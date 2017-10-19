package com.zlycare.web.boss_24.utils.common.utils;

import com.zlycare.web.boss_24.utils.common.dto.HttpClientResultDto;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.CookieStore;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.*;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContextBuilder;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Nullable;
import javax.net.ssl.SSLContext;
import java.io.IOException;
import java.net.URI;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.List;
import java.util.Map;

/**
 * Created by tonydeng on 15/1/14.
 */
public class HttpsClient {
    private static final Logger log = LoggerFactory.getLogger(HttpsClient.class);

    /**
     * Https GET Method
     *
     * @param url
     * @return
     */
    public static String get(String url) {
        return get(url, null, null, null, null, null);
    }
    public static HttpClientResultDto getOfStatusCode(String url) {
        return getOfStatusCode(url, null, null, null, null, null);
    }
    /**
     * Https GET Method
     *
     * @param url
     * @param params
     * @return
     */
    public static String get(String url, List<NameValuePair> params) {
        return get(url, params, null, null, null, null);
    }
    public static HttpClientResultDto getOfStatusCode(String url, List<NameValuePair> params) {
        return getOfStatusCode(url, params, null, null, null, null);
    }
    /**
     * Https GET Method
     *
     * @param url
     * @param headers
     * @return
     */
    public static String get(String url, Map<String, String> headers) {
        return get(url, null, headers, null, null, null);
    }
    public static HttpClientResultDto getOfStatusCode(String url, Map<String, String> headers) {
        return getOfStatusCode(url, null, headers, null, null, null);
    }
    /**
     * Https GET Method
     *
     * @param url
     * @param cookies
     * @param cookieMomain
     * @return
     */
    public static String get(String url, Map<String, String> cookies, String cookieMomain) {
        return get(url, null, null, cookies, cookieMomain, null);
    }
    public static HttpClientResultDto getOfStatusCode(String url, Map<String, String> cookies, String cookieMomain) {
        return getOfStatusCode(url, null, null, cookies, cookieMomain, null);
    }
    /**
     * Https GET Method
     *
     * @param url
     * @param params
     * @param headers
     * @param cookies
     * @param cookieMomain
     * @param charset
     * @return
     */
    public static String get(String url, List<NameValuePair> params, Map<String, String> headers,
                             Map<String, String> cookies, String cookieMomain,
                             String charset) {
//        CloseableHttpResponse response = null;
//        try {
//            if (StringUtils.isEmpty(charset))
//                charset = HttpClient.CHARSET;
//
//            HttpGet httpGet = new HttpGet(url);
//
//            if (MapUtils.isNotEmpty(headers)) {
//                Header[] defaults = httpGet.getAllHeaders();
//                for (Header h : defaults) {
//                    httpGet.removeHeader(h);
//                }
//                for (Map.Entry<String, String> entry : headers.entrySet()) {
//                    httpGet.setHeader(entry.getKey(), entry.getValue());
//                    if (log.isDebugEnabled()) {
//                        log.debug("set header:'" + entry.getKey() + ":" + entry.getValue() + "'");
//                    }
//                }
//            }
//
//            if (CollectionUtils.isNotEmpty(params)) {
//                String query = EntityUtils.toString(new UrlEncodedFormEntity(params, charset));
//                httpGet.setURI(new URI(httpGet.getURI().toString() + "?" + query));
//            }
//
//            CloseableHttpClient httpClient = createSSLClientDefault();
//
//            if (cookies != null) {
//                HttpClientContext context = HttpClientContext.create();
//                CookieStore cookieStore = new BasicCookieStore();
//                BasicClientCookie cookie = null;
//                for (Map.Entry<String, String> entry : cookies.entrySet()) {
//                    cookie = new BasicClientCookie(entry.getKey(), entry.getValue());
//                    cookie.setVersion(0);
//                    if (StringUtils.isNotBlank(cookieMomain))
//                        cookie.setDomain(cookieMomain);
//                    else
//                        cookie.setDomain(".duoquyuedu.com");
//                    cookie.setPath("/");
//                    cookieStore.addCookie(cookie);
//                }
//                context.setCookieStore(cookieStore);
//                response = httpClient.execute(httpGet, context);
//            } else {
//                response = httpClient.execute(httpGet);
//            }
//
//            int statusCode = response.getStatusLine().getStatusCode();
//            if (statusCode != HttpClient.OK) {
//                httpGet.abort();
//                throw new RuntimeException("HttpClient,error status code :" + statusCode);
//            }
//            HttpEntity entity = response.getEntity();
//            String result = null;
//            if (entity != null) {
//                result = EntityUtils.toString(entity, "utf-8");
//            }
//            EntityUtils.consume(entity);
//            response.close();
//            return result;
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            if (response != null)
//                try {
//                    response.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//        }
        return getOfStatusCode(url,params,headers,cookies,cookieMomain,charset).getEntity();
    }

    public static HttpClientResultDto getOfStatusCode(String url, List<NameValuePair> params, Map<String, String> headers,
                                                      Map<String, String> cookies, String cookieMomain,
                                                      String charset) {

        //结果对象
        HttpClientResultDto resultDto = new HttpClientResultDto();

        CloseableHttpResponse response = null;
        try {
            if (StringUtils.isEmpty(charset))
                charset = com.zlycare.web.boss_24.utils.common.utils.HttpClient.CHARSET;

            HttpGet httpGet = new HttpGet(url);

            if (MapUtils.isNotEmpty(headers)) {
                Header[] defaults = httpGet.getAllHeaders();
                for (Header h : defaults) {
                    httpGet.removeHeader(h);
                }
                for (Map.Entry<String, String> entry : headers.entrySet()) {
                    httpGet.setHeader(entry.getKey(), entry.getValue());
                    if (log.isDebugEnabled()) {
                        log.debug("set header:'" + entry.getKey() + ":" + entry.getValue() + "'");
                    }
                }
            }

            if (CollectionUtils.isNotEmpty(params)) {
                String query = EntityUtils.toString(new UrlEncodedFormEntity(params, charset));
                httpGet.setURI(new URI(httpGet.getURI().toString() + "?" + query));
            }

            CloseableHttpClient httpClient = createSSLClientDefault();

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
                response = httpClient.execute(httpGet, context);
            } else {
                response = httpClient.execute(httpGet);
            }

            int statusCode = response.getStatusLine().getStatusCode();
            resultDto.setStatusCode(statusCode);

            if (statusCode != HttpClient.OK) {
                httpGet.abort();
            }else{
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    resultDto.setEntity(EntityUtils.toString(entity, charset));
                }
                EntityUtils.consume(entity);
            }

            response.close();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (response != null)
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }

        return resultDto;
    }

    /**
     * Https POST byte array
     *
     * @param url
     * @param headers
     * @param body
     * @return
     */
    public static String post(@Nullable String url, @Nullable Map<String, String> headers, @Nullable byte[] body) {
        return _post(url, headers, null, null, body, null, null);
    }
    public static HttpClientResultDto postOfStatusCode(@Nullable String url, @Nullable Map<String, String> headers, @Nullable byte[] body) {
        return _postOfStatusCode(url, headers, null, null, body, null, null);
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
    public static String post(@Nullable String url, @Nullable Map<String, String> headers, @Nullable byte[] body, @Nullable String charset) {
        return _post(url, headers, null, null, body, null, charset);
    }
    public static HttpClientResultDto postOfStatusCode(@Nullable String url, @Nullable Map<String, String> headers, @Nullable byte[] body, @Nullable String charset) {
        return _postOfStatusCode(url, headers, null, null, body, null, charset);
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
    public static String post(@Nullable String url, @Nullable Map<String, String> headers,
                              @Nullable Map<String, String> cookies, @Nullable String cookieMomain,
                              @Nullable byte[] body, @Nullable String charset) {
        return _post(url, headers, cookies, cookieMomain, body, null, charset);
    }
    public static HttpClientResultDto postOfStatusCode(@Nullable String url, @Nullable Map<String, String> headers,
                                                       @Nullable Map<String, String> cookies, @Nullable String cookieMomain,
                                                       @Nullable byte[] body, @Nullable String charset) {
        return _postOfStatusCode(url, headers, cookies, cookieMomain, body, null, charset);
    }
    /**
     * Https POST form
     *
     * @param url
     * @param form
     * @param charset
     * @return
     */
    public static String post(String url, List<NameValuePair> form, String charset) {
        return _post(url, null, null, null, null, form, charset);
    }
    public static HttpClientResultDto postOfStatusCode(String url, List<NameValuePair> form, String charset) {
        return _postOfStatusCode(url, null, null, null, null, form, charset);
    }

    /**
     * Https post的私有方法
     *
     * @param url
     * @param headers
     * @param cookies
     * @param cookieMomain
     * @param body
     * @param formparams
     * @param charset
     * @return
     */
    private static String _post(String url, Map<String, String> headers,
                                @Nullable Map<String, String> cookies, @Nullable String cookieMomain,
                                @Nullable byte[] body, List<NameValuePair> formparams, @Nullable String charset) {
//        CloseableHttpResponse response = null;
//        try {
//            //defualt charset
//            if (StringUtils.isEmpty(charset))
//                charset = HttpClient.CHARSET;
//
//            HttpPost httpPost = new HttpPost(url);
//            //set headers
//            if (MapUtils.isNotEmpty(headers)) {
//                Header[] defaults = httpPost.getAllHeaders();
//                for (Header h : defaults) {
//                    httpPost.removeHeader(h);
//                }
//                for (Map.Entry<String, String> entry : headers.entrySet()) {
//                    httpPost.setHeader(entry.getKey(), entry.getValue());
//                    if (log.isDebugEnabled()) {
//                        log.debug("set header:'" + entry.getKey() + ":" + entry.getValue() + "'");
//                    }
//                }
//            }
//            //添加post body
//            if (ArrayUtils.isNotEmpty(body)) {
//                httpPost.setEntity(new ByteArrayEntity(body));
//            } else if (CollectionUtils.isNotEmpty(formparams)) {
//                httpPost.setEntity(new UrlEncodedFormEntity(formparams, charset));
//            }
//
//            CloseableHttpClient httpClient = createSSLClientDefault();
//            //set cookies
//            if (cookies != null) {
//                HttpClientContext context = HttpClientContext.create();
//                CookieStore cookieStore = new BasicCookieStore();
//                BasicClientCookie cookie = null;
//                for (Map.Entry<String, String> entry : cookies.entrySet()) {
//                    cookie = new BasicClientCookie(entry.getKey(), entry.getValue());
//                    cookie.setVersion(0);
//                    if (StringUtils.isNotBlank(cookieMomain))
//                        cookie.setDomain(cookieMomain);
//                    else
//                        cookie.setDomain(".duoquyuedu.com");
//                    cookie.setPath("/");
//                    cookieStore.addCookie(cookie);
//                }
//                context.setCookieStore(cookieStore);
//                response = httpClient.execute(httpPost, context);
//            } else {
//                response = httpClient.execute(httpPost);
//            }
//
//            int statusCode = response.getStatusLine().getStatusCode();
//            if (statusCode != HttpClient.OK) {
//                httpPost.abort();
//                throw new RuntimeException("HttpClient,error status code :" + statusCode);
//            }
//            HttpEntity entity = response.getEntity();
//            String result = null;
//            if (entity != null) {
//                result = EntityUtils.toString(entity, charset);
//            }
//            EntityUtils.consume(entity);
//            response.close();
//            return result;
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            if (response != null)
//                try {
//                    response.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//        }
        return _postOfStatusCode(url,headers,cookies,cookieMomain,body,formparams,charset).getEntity();
    }

    /**
     * Https post的私有方法,该post方法返回的信息包括状态码
     */
    private static HttpClientResultDto _postOfStatusCode(String url, Map<String, String> headers,
                                                         @Nullable Map<String, String> cookies, @Nullable String cookieMomain,
                                                         @Nullable byte[] body, List<NameValuePair> formparams, @Nullable String charset) {
        //结果对象
        HttpClientResultDto resultDto = new HttpClientResultDto();

        CloseableHttpResponse response = null;
        try {
            //defualt charset
            if (StringUtils.isEmpty(charset))
                charset = HttpClient.CHARSET;

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

            CloseableHttpClient httpClient = createSSLClientDefault();
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
                response = httpClient.execute(httpPost, context);
            } else {
                response = httpClient.execute(httpPost);
            }

            int statusCode = response.getStatusLine().getStatusCode();
            resultDto.setStatusCode(statusCode);

            if (statusCode != HttpClient.OK) {
                httpPost.abort();
            }else{
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    resultDto.setEntity(EntityUtils.toString(entity, charset));
                }
                EntityUtils.consume(entity);
            }

            response.close();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (response != null) {
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return resultDto;
    }

    /**
     * PUT byte array
     *
     * @param url
     * @param headers
     * @param body
     * @return
     */
    public static String put(@Nullable String url, @Nullable Map<String, String> headers, @Nullable byte[] body) {
        return _put(url, headers, null, null, body, null, null);
    }

    /**
     * PUT byte array
     *
     * @param url
     * @param headers
     * @param body
     * @param charset
     * @return
     */
    public static String put(@Nullable String url, @Nullable Map<String, String> headers, @Nullable byte[] body, @Nullable String charset) {
        return _put(url, headers, null, null, body, null, charset);
    }

    /**
     * PUT byte array
     *
     * @param url
     * @param headers
     * @param cookies
     * @param cookieMomain
     * @param body
     * @param charset
     * @return
     */
    public static String put(@Nullable String url, @Nullable Map<String, String> headers,
                             @Nullable Map<String, String> cookies, @Nullable String cookieMomain,
                             @Nullable byte[] body, @Nullable String charset) {
        return _put(url, headers, cookies, cookieMomain, body, null, charset);
    }

    /**
     * PUT form
     *
     * @param url
     * @param form
     * @param charset
     * @return
     */
    public static String put(String url, List<NameValuePair> form, String charset) {
        return _put(url, null, null, null, null, form, charset);
    }

    private static String _put(String url, Map<String, String> headers,
                               @Nullable Map<String, String> cookies, @Nullable String cookieMomain,
                               @Nullable byte[] body, List<NameValuePair> formparams, @Nullable String charset) {
        CloseableHttpResponse response = null;
        try {
            //defualt charset
            if (StringUtils.isEmpty(charset))
                charset = HttpClient.CHARSET;

            HttpPut httpPut = new HttpPut(url);
            //set headers
            if (MapUtils.isNotEmpty(headers)) {
                Header[] defaults = httpPut.getAllHeaders();
                for (Header h : defaults) {
                    httpPut.removeHeader(h);
                }
                for (Map.Entry<String, String> entry : headers.entrySet()) {
                    httpPut.setHeader(entry.getKey(), entry.getValue());
                    if (log.isDebugEnabled()) {
                        log.debug("set header:'" + entry.getKey() + ":" + entry.getValue() + "'");
                    }
                }
            }
            //添加post body
            if (ArrayUtils.isNotEmpty(body)) {
                httpPut.setEntity(new ByteArrayEntity(body));
            } else if (CollectionUtils.isNotEmpty(formparams)) {
                httpPut.setEntity(new UrlEncodedFormEntity(formparams, charset));
            }

            CloseableHttpClient httpClient = createSSLClientDefault();
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
                response = httpClient.execute(httpPut, context);
            } else {
                response = httpClient.execute(httpPut);
            }

            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode != HttpClient.OK) {
                httpPut.abort();
                throw new RuntimeException("HttpClient,error status code :" + statusCode);
            }
            HttpEntity entity = response.getEntity();
            String result = null;
            if (entity != null) {
                result = EntityUtils.toString(entity, charset);
            }
            EntityUtils.consume(entity);
            response.close();
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (response != null)
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
        return null;
    }

    /**
     * Https Delete Method
     *
     * @param url
     * @return
     */
    public static String delete(String url) {
        return delete(url, null, null, null, null, null);
    }

    /**
     * Https Delete Method
     *
     * @param url
     * @param params
     * @return
     */
    public static String delete(String url, List<NameValuePair> params) {
        return delete(url, params, null, null, null, null);
    }

    /**
     * Https Delete Method
     *
     * @param url
     * @param headers
     * @return
     */
    public static String delete(String url, Map<String, String> headers) {
        return delete(url, null, headers, null, null, null);
    }

    /**
     * Https Delete Method
     *
     * @param url
     * @param cookies
     * @param cookieMomain
     * @return
     */
    public static String delete(String url, Map<String, String> cookies, String cookieMomain) {
        return delete(url, null, null, cookies, cookieMomain, null);
    }

    /**
     * Https Delete Method
     *
     * @param url
     * @param params
     * @param headers
     * @param cookies
     * @param cookieMomain
     * @param charset
     * @return
     */
    public static String delete(String url, List<NameValuePair> params, Map<String, String> headers,
                                Map<String, String> cookies, String cookieMomain,
                                String charset) {
        CloseableHttpResponse response = null;
        try {
            if (StringUtils.isEmpty(charset))
                charset = HttpClient.CHARSET;

            HttpDelete httpDelete = new HttpDelete(url);

            if (MapUtils.isNotEmpty(headers)) {
                Header[] defaults = httpDelete.getAllHeaders();
                for (Header h : defaults) {
                    httpDelete.removeHeader(h);
                }
                for (Map.Entry<String, String> entry : headers.entrySet()) {
                    httpDelete.setHeader(entry.getKey(), entry.getValue());
                    if (log.isDebugEnabled()) {
                        log.debug("set header:'" + entry.getKey() + ":" + entry.getValue() + "'");
                    }
                }
            }

            if (CollectionUtils.isNotEmpty(params)) {
                String query = EntityUtils.toString(new UrlEncodedFormEntity(params, charset));
                httpDelete.setURI(new URI(httpDelete.getURI().toString() + "?" + query));
            }

            CloseableHttpClient httpClient = createSSLClientDefault();

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
                response = httpClient.execute(httpDelete, context);
            } else {
                response = httpClient.execute(httpDelete);
            }

            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode != HttpClient.OK) {
                httpDelete.abort();
                throw new RuntimeException("HttpClient,error status code :" + statusCode);
            }
            HttpEntity entity = response.getEntity();
            String result = null;
            if (entity != null) {
                result = EntityUtils.toString(entity, "utf-8");
            }
            EntityUtils.consume(entity);
            response.close();
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (response != null)
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
        return null;
    }

    public static CloseableHttpClient createSSLClientDefault() {
        try {
            SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy() {
                //信任所有
                public boolean isTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                    return true;
                }
            }).build();
            SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext);
            return HttpClients.custom().setSSLSocketFactory(sslsf).build();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyStoreException e) {
            e.printStackTrace();
        }
        return HttpClients.createDefault();
    }
}
