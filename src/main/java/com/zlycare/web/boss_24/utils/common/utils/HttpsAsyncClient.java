package com.zlycare.web.boss_24.utils.common.utils;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.CookieStore;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.conn.ssl.SSLContextBuilder;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClients;
import org.apache.http.nio.conn.ssl.SSLIOSessionStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Nullable;
import javax.net.ssl.SSLContext;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Future;

/**
 * Description 异步的HttpsClient
 * Created by Thinkpad on 2015/3/18.
 */
public class HttpsAsyncClient {

    private static final Logger log = LoggerFactory.getLogger(HttpsAsyncClient.class);

    /**
     * Https POST byte array
     *
     * @param url
     * @param headers
     * @param body
     */
    public static void post(@Nullable String url, @Nullable Map<String, String> headers, @Nullable byte[] body) {
        _post(url, headers, null, null, body, null, null);
    }
    /**
     * Https POST byte array
     *
     * @param url
     * @param headers
     * @param body
     * @param charset
     * @return
     */
    public static void post(@Nullable String url, @Nullable Map<String, String> headers, @Nullable byte[] body, @Nullable String charset) {
        _post(url, headers, null, null, body, null, charset);
    }
    /**
     * Https POST byte array
     *
     * @param url
     * @param headers
     * @param cookies
     * @param cookieMomain
     * @param body
     * @param charset
     * @return
     */
    public static void post(@Nullable String url, @Nullable Map<String, String> headers,
                            @Nullable Map<String, String> cookies, @Nullable String cookieMomain,
                            @Nullable byte[] body, @Nullable String charset) {
        _post(url, headers, cookies, cookieMomain, body, null, charset);
    }
    /**
     * Https POST form
     *
     * @param url
     * @param form
     * @param charset
     * @return
     */
    public static void post(String url, List<NameValuePair> form, String charset) {
        _post(url, null, null, null, null, form, charset);
    }

    /**
     * Description HttpAsyncClient,HttpClient的异步请求,不返回结果
     *
     * @param url
     * @param headers
     * @param cookies
     * @param cookieMomain
     * @param body
     * @param formparams
     * @param charset
     */
    private static void _post(String url, Map<String, String> headers,
                              @Nullable Map<String, String> cookies, @Nullable String cookieMomain,
                              @Nullable byte[] body, List<NameValuePair> formparams, @Nullable String charset) {

        //结果对象
//        HttpClientResultDto resultDto = new HttpClientResultDto();

        Future<HttpResponse> future = null;
//        HttpResponse response = null;
        CloseableHttpAsyncClient httpAsyncClient = null;

        try {
            //defualt charset
            if (StringUtils.isEmpty(charset))
                charset = com.zlycare.web.boss_24.utils.common.utils.HttpClient.CHARSET;

            HttpPost httpPost = new HttpPost(url);
            //set headers
            if (MapUtils.isNotEmpty(headers)) {
                Header[] defaults = httpPost.getAllHeaders();
                for (Header h : defaults) {
                    httpPost.removeHeader(h);
                }
                for (Map.Entry<String, String> entry : headers.entrySet()) {
                    httpPost.setHeader(entry.getKey(), entry.getValue());
                    if (log.isDebugEnabled()) {
                        log.debug("set header:'" + entry.getKey() + ":" + entry.getValue() + "'");
                    }
                }
            }
            //添加post body
            if (ArrayUtils.isNotEmpty(body)) {
                httpPost.setEntity(new ByteArrayEntity(body));
            } else if (CollectionUtils.isNotEmpty(formparams)) {
                httpPost.setEntity(new UrlEncodedFormEntity(formparams, charset));
            }

            httpAsyncClient = createSSLAsyncClientDefault();

            //set cookies
            if (cookies != null) {
                HttpClientContext context = HttpClientContext.create();
                CookieStore cookieStore = new BasicCookieStore();
                BasicClientCookie cookie = null;
                for (Map.Entry<String, String> entry : cookies.entrySet()) {
                    cookie = new BasicClientCookie(entry.getKey(), entry.getValue());
                    cookie.setVersion(0);
                    if (StringUtils.isNotBlank(cookieMomain))
                        cookie.setDomain(cookieMomain);
                    else
                        cookie.setDomain(".duoquyuedu.com");
                    cookie.setPath("/");
                    cookieStore.addCookie(cookie);
                }
                context.setCookieStore(cookieStore);

                httpAsyncClient.start();
                future = httpAsyncClient.execute(httpPost, context, null);
            } else {
                httpAsyncClient.start();
                future = httpAsyncClient.execute(httpPost, null);
            }

//            response = future.get();

//            int statusCode = response.getStatusLine().getStatusCode();
//            resultDto.setStatusCode(statusCode);



//            if (statusCode != HttpClient.OK) {
//                httpPost.abort();
//            }else{
//                HttpEntity entity = response.getEntity();
//                if (entity != null) {
//                    resultDto.setEntity(EntityUtils.toString(entity, charset));
//                }
//                EntityUtils.consume(entity);
//            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (httpAsyncClient != null)
                try {
                    httpAsyncClient.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }

//        return resultDto;
    }

    private static CloseableHttpAsyncClient createSSLAsyncClientDefault(){

        try {
            SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy() {
                //信任所有
                public boolean isTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                    return true;
                }
            }).build();

            SSLIOSessionStrategy sslSessionStrategy = new SSLIOSessionStrategy(sslContext,
                    SSLIOSessionStrategy.ALLOW_ALL_HOSTNAME_VERIFIER);

            return HttpAsyncClients.custom().setSSLStrategy(sslSessionStrategy).build();

        } catch (KeyManagementException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyStoreException e) {
            e.printStackTrace();
        }

        return null;
    }
}