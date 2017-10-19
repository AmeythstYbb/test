package com.zlycare.web.boss_24.utils.common.utils;


import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CookieStore;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.EntityBuilder;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.SSLContext;
import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;

/**
 * Description:
 *
 * @author yz.wu
 */
public class HttpClient {

    public static final int OK = 200;        // OK: Success!
    public static final int NOT_MODIFIED = 304;        // Not Modified: There was no new data to return.
    public static final int BAD_REQUEST = 400;        // Bad Request: The request was invalid.  An accompanying error message will explain why. This is the status code will be returned during rate limiting.
    public static final int NOT_AUTHORIZED = 401;        // Not Authorized: Authentication credentials were missing or incorrect.
    public static final int FORBIDDEN = 403;        // Forbidden: The request is understood, but it has been refused.  An accompanying error message will explain why.
    public static final int NOT_FOUND = 404;        // Not Found: The URI requested is invalid or the resource requested, such as a user, does not exists.
    public static final int NOT_ACCEPTABLE = 406;        // Not Acceptable: Returned by the Search API when an invalid format is specified in the request.
    public static final int INTERNAL_SERVER_ERROR = 500;        // Internal Server Error: Something is broken.  Please post to the group so the Weibo team can investigate.
    public static final int BAD_GATEWAY = 502;        // Bad Gateway: Weibo is down or being upgraded.
    public static final int SERVICE_UNAVAILABLE = 503;        // Service Unavailable: The Weibo servers are up, but overloaded with requests. Try again later. The search and trend methods use this to indicate when you are being rate limited.

    public static final String USER_AGENT = "Mozilla/5.0 (Windows; U; Windows NT 5.1; en-US) AppleWebKit/534.3 (KHTML, like Gecko) Chrome/6.0.472.63 Safari/534.3";
    public static final String CHARSET = "UTF-8";

    private static final Logger logger = LoggerFactory.getLogger(HttpClient.class);

    private static CloseableHttpClient httpClient;

    static {
        SSLContext sslContext = SSLContexts.createSystemDefault();
        SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(
                sslContext,
                SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);

        RequestConfig globalConfig = RequestConfig.custom()
                .setCookieSpec(CookieSpecs.BROWSER_COMPATIBILITY)
                .setSocketTimeout(10000)
                .setConnectTimeout(10000)
                .build();

        PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
        cm.setMaxTotal(500);
        cm.setDefaultMaxPerRoute(100);
        httpClient = HttpClients.custom()
                .setConnectionManager(cm)
                .setSSLSocketFactory(sslsf)
                .setDefaultRequestConfig(globalConfig)
                .build();
    }

    public static CloseableHttpClient getClient() {
        return httpClient;
    }

    /**
     * post
     *
     * @param url
     * @param formparams
     * @return
     */
    public static String post(String url, List<NameValuePair> formparams) {
        HttpPost post = null;
        CloseableHttpResponse response = null;
        try {
            post = new HttpPost(url);
            post.setHeader("User-Agent", USER_AGENT);
            if (CollectionUtils.isNotEmpty(formparams)) {
                post.setEntity(new UrlEncodedFormEntity(formparams, CHARSET));
            }
            response = getClient().execute(post);
            int responseCode = response.getStatusLine().getStatusCode();
            if (responseCode == OK) {
                return EntityUtils.toString(response.getEntity());
            } else {
                logger.error(getCause(responseCode));
            }
        } catch (UnsupportedEncodingException e) {
            logger.error(e.toString());
        } catch (ClientProtocolException e) {
            logger.error(e.toString());
        } catch (ParseException e) {
            logger.error(e.toString());
        } catch (IOException e) {
            logger.error(e.toString());
        } catch (Exception e) {
            logger.error(e.toString());
        } finally {
            if (response != null) {
                try {
                    response.close();
                } catch (IOException e) {
                    logger.error(e.toString());
                }
            }
            post.releaseConnection();
        }

        return null;
    }

    public static String post(String url, String json) {
        HttpPost post = null;
        CloseableHttpResponse response = null;
        try {
            post = new HttpPost(url);
            post.setHeader("User-Agent", USER_AGENT);

            EntityBuilder eb = EntityBuilder.create();
            eb.setText(json);
            eb.setContentEncoding(CHARSET);
            eb.setContentType(ContentType.APPLICATION_JSON);

            post.setEntity(eb.build());

            response = getClient().execute(post);
            int responseCode = response.getStatusLine().getStatusCode();
            if (responseCode == OK) {
                return EntityUtils.toString(response.getEntity());
            } else {
                logger.error(getCause(responseCode));
            }
        } catch (UnsupportedEncodingException e) {
            logger.error(e.toString());
        } catch (ClientProtocolException e) {
            logger.error(e.toString());
        } catch (ParseException e) {
            logger.error(e.toString());
        } catch (IOException e) {
            logger.error(e.toString());
        } catch (Exception e) {
            logger.error(e.toString());
        } finally {
            if (response != null) {
                try {
                    response.close();
                } catch (IOException e) {
                    logger.error(e.toString());
                }
            }
            post.releaseConnection();
        }

        return null;
    }

    /**
     * post with headers
     *
     * @param url
     * @param formparams
     * @param headers
     * @return
     */
    public static String post(String url, List<NameValuePair> formparams, Map<String, String> headers) {
        HttpPost post = null;
        CloseableHttpResponse response = null;
        try {
            post = new HttpPost(url);
            if (headers != null) {
                for (Map.Entry<String, String> entry : headers.entrySet()) {
                    post.setHeader(entry.getKey(), entry.getValue());
                    if (logger.isDebugEnabled()) {
                        logger.debug(entry.getKey() + ":" + entry.getValue());
                    }
                }
            }
            post.setHeader("User-Agent", USER_AGENT);

            if (CollectionUtils.isNotEmpty(formparams)) {
                post.setEntity(new UrlEncodedFormEntity(formparams, CHARSET));
            }
            response = getClient().execute(post);
            int responseCode = response.getStatusLine().getStatusCode();
            if (responseCode == OK) {
                return EntityUtils.toString(response.getEntity());
            } else {
                logger.error(getCause(responseCode));
            }
        } catch (UnsupportedEncodingException e) {
            logger.error(e.toString());
        } catch (ClientProtocolException e) {
            logger.error(e.toString());
        } catch (ParseException e) {
            logger.error(e.toString());
        } catch (IOException e) {
            logger.error(e.toString());
        } catch (Exception e) {
            logger.error(e.toString());
        } finally {
            if (response != null) {
                try {
                    response.close();
                } catch (IOException e) {
                    logger.error(e.toString());
                }
            }
            post.releaseConnection();
        }

        return null;
    }

    private static void checkAndMakeParentDirecotry(String fullName) {
        File file = new File(fullName).getParentFile();
        if (!file.exists()) {
            file.mkdirs();
        }
    }
    public static boolean fileDownload(String url, String file) {
        return fileDownload(url,file,USER_AGENT);
    }
    /**
     * fileDownload
     *
     * @param url
     * @return
     * @author Scott Lee
     */
    public static boolean fileDownload(String url, String file, String ua) {
        class Proceeding {
            long amount;
            long current;
            Long rate;
            String url;

            public Proceeding(String url, long amount, int current) {
                this.amount = amount;
                this.current = current;
                this.url = url;
            }

            public void add(int bt) {
                this.current += bt;
                Long rate = this.current * 100 / amount;
                if (!rate.equals(this.rate)) {
                    this.rate = rate;
                    if (this.rate % 10 == 0)
                        logger.info("download total [{}k] rate {}%: {}", amount / 1000, rate, url);
                }

            }
        }
        boolean success = false;
        HttpGet get = null;
        CloseableHttpResponse response = null;
        FileOutputStream fs = null;
        try {
            get = new HttpGet(url);
//            get.setHeader("User-Agent", "/Mozilla/5.0 iPhone (Windows NT 6.1) Chrome/28.0.1468.0 Safari/537.36");
            get.setHeader("User-Agent", ua);
            response = getClient().execute(get);
            int responseCode = response.getStatusLine().getStatusCode();
            HttpEntity entity = response.getEntity();
            if (responseCode == OK && entity != null) {
                InputStream inStream = entity.getContent();
//                System.out.println(entity.getContentLength());
                Proceeding proceeding = new Proceeding(url, entity.getContentLength(), 0);
                int bt = 0;
                checkAndMakeParentDirecotry(file);
                fs = new FileOutputStream(file);
                byte[] buffer = new byte[1024];
                while ((bt = inStream.read(buffer)) != -1) {
                    proceeding.add(bt);
                    fs.write(buffer, 0, bt);
                }
                logger.info("download done: " + url);
                success = true;
            }
        } catch (Throwable e) {
            success = false;
            logger.error("fileDownload Exception", e);
        } finally {
            try {
                if (fs != null) fs.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (response != null) {
                try {
                    response.close();
                } catch (IOException e) {
                    logger.error(e.toString());
                }
            }
            get.releaseConnection();
        }
        return success;
    }

    /**
     * getByte
     *
     * @param url
     * @param formparams
     * @return
     */
    public static byte[] getByte(String url, List<NameValuePair> formparams) {
        HttpGet get = null;
        CloseableHttpResponse response = null;
        try {
            get = new HttpGet(url);
            get.setHeader("User-Agent", USER_AGENT);
            if (CollectionUtils.isNotEmpty(formparams)) {
                String query = EntityUtils.toString(new UrlEncodedFormEntity(formparams, CHARSET));
                get.setURI(new URI(get.getURI().toString() + "?" + query));
            }

            if (logger.isDebugEnabled()) {
                logger.debug("get byte array by url:'" + get.getURI() + "'");
            }

            response = getClient().execute(get);
            int responseCode = response.getStatusLine().getStatusCode();
            if (responseCode == OK) {
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    return EntityUtils.toByteArray(entity);
                }
//                return EntityUtils.toString(response.getEntity(), CHARSET);
            } else {
                logger.error("GET Bytes url:'" + get.getURI() + "' Error:" + getCause(responseCode));
            }
        } catch (ParseException e) {
            logger.error("GET Bytes  url:'" + get.getURI() + "' ParseException Error:" + e.toString());
        } catch (UnsupportedEncodingException e) {
            logger.error("GET Bytes  url:'" + get.getURI() + "' UnsupportedEncodingException Error:" + e.toString());
        } catch (ClientProtocolException e) {
            logger.error("GET Bytes  url:'" + get.getURI() + "' ClientProtocolException Error:" + e.toString());
        } catch (IOException e) {
            logger.error("GET Bytes  url:'" + get.getURI() + "' IOException Error:" + e.toString());
        } catch (URISyntaxException e) {
            logger.error("GET Bytes  url:'" + get.getURI() + "' URISyntaxException Error:" + e.toString());
        } finally {
            if (response != null) {
                try {
                    response.close();
                } catch (IOException e) {
                    logger.error(e.toString());
                }
            }
            get.releaseConnection();
        }

        return null;
    }

    /**
     * getByte
     *
     * @param url
     * @return
     */
    public static byte[] getByte(String url) {
        return getByte(url, null);
    }

    /**
     * get with headers and cookies
     *
     * @param url
     * @param params
     * @param headers
     * @param cookies
     * @return
     */
    public static String get(String url, List<NameValuePair> params,
                             Map<String, String> headers, Map<String, String> cookies,
                             String charset) {
        HttpGet get = null;
        CloseableHttpResponse response = null;
        try {

            if (StringUtils.isEmpty(charset))
                charset = CHARSET;

            get = new HttpGet(url);
            Header[] defaults = get.getAllHeaders();
            for (Header h : defaults) {
                get.removeHeader(h);
            }

            if (headers != null) {
                for (Map.Entry<String, String> entry : headers.entrySet()) {
                    get.setHeader(entry.getKey(), entry.getValue());
                    if (logger.isDebugEnabled()) {
                        logger.debug("set header:'" + entry.getKey() + ":" + entry.getValue() + "'");
                    }
                }
            }
            if (CollectionUtils.isNotEmpty(params)) {
                String query = EntityUtils.toString(new UrlEncodedFormEntity(params, charset));
                get.setURI(new URI(get.getURI().toString() + "?" + query));
            }

            if (cookies != null) {
                HttpClientContext context = HttpClientContext.create();
                CookieStore cookieStore = new BasicCookieStore();
                BasicClientCookie cookie = null;
                for (Map.Entry<String, String> entry : cookies.entrySet()) {
                    cookie = new BasicClientCookie(entry.getKey(), entry.getValue());
                    cookie.setVersion(0);
                    cookie.setDomain(".duoquyuedu.com");
                    cookie.setPath("/");
                    cookieStore.addCookie(cookie);
                }
                context.setCookieStore(cookieStore);
                response = getClient().execute(get, context);
            } else {
                response = getClient().execute(get);
            }

            int responseCode = response.getStatusLine().getStatusCode();
            if (responseCode == OK) {
                return EntityUtils.toString(response.getEntity(), charset);
            } else {
                logger.error("GET String url:'" + get.getURI() + "' Error:" + getCause(responseCode));
            }
        } catch (ParseException e) {
            logger.error("GET String  url:'" + get.getURI() + "' ParseException Error:" + e.toString());
        } catch (UnsupportedEncodingException e) {
            logger.error("GET String  url:'" + get.getURI() + "' UnsupportedEncodingException Error:" + e.toString());
        } catch (ClientProtocolException e) {
            logger.error("GET String  url:'" + get.getURI() + "' ClientProtocolException Error:" + e.toString());
        } catch (IOException e) {
            logger.error("GET String  url:'" + get.getURI() + "' IOException Error:" + e.toString());
        } catch (URISyntaxException e) {
            logger.error("GET String  url:'" + get.getURI() + "' URISyntaxException Error:" + e.toString());
        } finally {
            if (response != null) {
                try {
                    response.close();
                } catch (IOException e) {
                    logger.error(e.toString());
                }
            }
            get.releaseConnection();
        }

        return null;
    }

    /**
     * get
     *
     * @param url
     * @return
     */
    public static String get(String url) {
        return get(url, null, null, null, null);
    }

    /**
     * get with charset
     *
     * @param url
     * @param params
     * @param charset
     * @return
     */
    public static String get(String url, List<NameValuePair> params, String charset) {
        return get(url, params, null, null, charset);
    }

    /**
     * get
     *
     * @param url
     * @param params
     * @return
     */
    public static String get(String url, List<NameValuePair> params) {
        return get(url, params, null, null, null);
    }

    /**
     * get with headers
     *
     * @param url
     * @param params
     * @param headers
     * @return
     */
    public static String get(String url, List<NameValuePair> params, Map<String, String> headers) {
        return get(url, params, headers, null, null);
    }


    private static String getCause(int statusCode) {
        String cause = null;
        switch (statusCode) {
            case NOT_MODIFIED:
                break;
            case BAD_REQUEST:
                cause = "The request was invalid.";
                break;
            case NOT_AUTHORIZED:
                cause = "Authentication credentials were missing or incorrect.";
                break;
            case FORBIDDEN:
                cause = "The request is understood, but it has been refused.";
                break;
            case NOT_FOUND:
                cause = "The URI requested is invalid or the resource requested does not exists.";
                break;
            case NOT_ACCEPTABLE:
                cause = "Returned by the Search API when an invalid format is specified in the request.";
                break;
            case INTERNAL_SERVER_ERROR:
                cause = "Something is broken.";
                break;
            case BAD_GATEWAY:
                cause = "target is down or being upgraded.";
                break;
            case SERVICE_UNAVAILABLE:
                cause = "Service Unavailable";
                break;
            default:
                cause = "";
        }

        return statusCode + ":" + cause;
    }


}
