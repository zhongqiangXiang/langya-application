package com.ideacome.common.util;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.config.SocketConfig;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.ideacome.common.dto.ErrorEnum;
import com.ideacome.common.dto.HttpClientRes;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HttpClientUtil {
    
    private static final int timeout = 30;
    private static PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
    private static CloseableHttpClient httpclient = null;
    private static CloseableHttpClient httpsclient = null;
    private static RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(timeout * 1000)// 通讯过程中的超时时间。
                    .setConnectTimeout(timeout * 1000)// 建立网络连接的超时时间
                    .setConnectionRequestTimeout(timeout * 1000)// 等待连接池分配的超时时间
                    .build();
    private static SocketConfig socketConfig = SocketConfig.custom().setSoTimeout(timeout * 1000).build();
    SSLConnectionSocketFactory ssl = null;
    static {
        log.info("***************************开始初始化http/https请求客户端对象*************************");
        cm.setMaxTotal(100);
        cm.setDefaultSocketConfig(socketConfig);
        httpclient = HttpClients.custom().setConnectionManager(cm).setDefaultRequestConfig(requestConfig).build();
        
        
        SSLContext sslContext;
        try {
            sslContext = new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy() {
                // 信任所有
                @Override
                public boolean isTrusted(java.security.cert.X509Certificate[] arg0, String arg1)
                                throws java.security.cert.CertificateException {
                    return true;
                }
            }).build();
            SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext, new HostnameVerifier() {
                @Override
                public boolean verify(String arg0, SSLSession arg1) {
                    return true;
                }
            });
            httpsclient = HttpClients.custom().setConnectionManager(cm).setSSLSocketFactory(sslsf).build();
        } catch (KeyManagementException | NoSuchAlgorithmException | KeyStoreException e) {
            e.printStackTrace();
        }
        log.info("***************************初始化http/https客户端对象成功***************************");
    }
    /**
     * 发送post请求（参数是json格式）
     * @param url
     * @param body
     * @param heads
     * @param writerCharset
     * @param readerCharset
     * @return
     */
    public static HttpClientRes sendPostInJson(String url, String body, Map<String, Object> heads, String writerCharset,
                    String readerCharset) {
        CloseableHttpClient client = null;
        if (url.startsWith("https")) {
            client = httpsclient;
        }else {
            client = httpclient;
        }
        if(client == null) {
        	log.info("请求客户端对象获取失败");
            return HttpClientRes.newFailure(ErrorEnum.failure, "请求客户端对象获取失败");
        }
        if (StringUtils.isBlank(writerCharset)) {
            writerCharset = "UTF-8";
        }
        if (StringUtils.isBlank(readerCharset)) {
            readerCharset = "UTF-8";
        }
        HttpPost httppost = new HttpPost(url);
        httppost.setHeader("Content-Type", "application/json;charset=UTF-8");
        httppost.setConfig(requestConfig);
        if (heads != null) {
            for (Map.Entry<String, Object> entry : heads.entrySet()) {
                httppost.setHeader(entry.getKey(), entry.getValue().toString());
            }
        }
        StringEntity entity = new StringEntity(body, writerCharset);
        entity.setContentType("application/json");
        entity.setChunked(false);// 不分块传输
        httppost.setEntity(entity);
        log.info("开始发送消息，发送地址：{},消息体：{},消息头：{}",url,body,heads);
        
        return doExecute(httppost, readerCharset,client);
    }
    
    /**
     * 发送post请求（参数是form-data格式）
     * @param url
     * @param body
     * @param heads
     * @param writerCharset
     * @param readerCharset
     * @return
     */
    public static HttpClientRes sendPostInFormdata(String url, Map<String,String> body, Map<String, Object> heads, String writerCharset,
                    String readerCharset) {
        CloseableHttpClient client = null;
        if (url.startsWith("https")) {
            client = httpsclient;
        }else {
            client = httpclient;
        }
        if(client == null) {
        	log.info("请求客户端对象获取失败");
            return HttpClientRes.newFailure(ErrorEnum.failure, "请求客户端初始化失败");
        }
        if (StringUtils.isBlank(writerCharset)) {
            writerCharset = "UTF-8";
        }
        if (StringUtils.isBlank(readerCharset)) {
            readerCharset = "UTF-8";
        }
        HttpPost httppost = new HttpPost(url);
        httppost.setConfig(requestConfig);
        if (heads != null) {
            for (Map.Entry<String, Object> entry : heads.entrySet()) {
                httppost.setHeader(entry.getKey(), entry.getValue().toString());
            }
        }
        
        List<BasicNameValuePair> paraList = new ArrayList<BasicNameValuePair>(body.size());
        for (Entry<String, String> pEntry : body.entrySet()) {
            if (null != pEntry.getValue()) {
                BasicNameValuePair nv = new BasicNameValuePair(pEntry.getKey(), pEntry.getValue());
                paraList.add(nv);
            }
        }
        // 使用UTF-8
        UrlEncodedFormEntity entity = new UrlEncodedFormEntity(paraList, Charset.forName(writerCharset));
        httppost.setEntity(entity);
        log.info("开始发送消息，发送地址：{},消息体：{},消息头：{}",url,body,heads);
        
        return doExecute(httppost, readerCharset,client);
    }
    /**
     * 发送Get请求
     * @param url
     * @param readerCharset
     * @return
     */
    public static HttpClientRes sendGet(String url,String readerCharset) {
        CloseableHttpClient client = null;
        if (url.startsWith("https")) {
            client = httpsclient;
        }else {
            client = httpclient;
        }
        if(client == null) {
        	log.info("请求客户端对象获取失败");
            return HttpClientRes.newFailure(ErrorEnum.failure, "请求客户端初始化失败");
        }
        if (StringUtils.isBlank(readerCharset)) {
            readerCharset = "UTF-8";
        }
        HttpGet httpGet = new HttpGet(url);
        httpGet.setConfig(requestConfig);
        log.info("开始发送消息，发送地址：{},消息体：{},消息头：{}",url);
        
        return doExecute(httpGet,readerCharset,client);
    }
    /**
     * post方式发送带附件的请求
     * @param url
     * @param body
     * @param heads
     * @param writerCharset
     * @param readerCharset
     * @param file
     * @return
     */
    public static HttpClientRes sendPostInFormdataWithFile(String url,Map<String,String> body, Map<String, Object> heads, 
                    String writerCharset,String readerCharset,List<InputStream> fileStreams,List<String> fileNames) {
        CloseableHttpClient client = null;
        if (url.startsWith("https")) {
            client = httpsclient;
        }else {
            client = httpclient;
        }
        if(client == null) {
        	log.info("请求客户端对象获取失败");
            return HttpClientRes.newFailure(ErrorEnum.failure, "请求客户端初始化失败");
        }
        log.info("com.ideacome.open.sdk.util.HttpClientUtil.sendPostInFormdataWithFile请求参数："
                        + "url-{},body-{},heads-{},writerCharset-{},readerCharset-{},fileNames-{}",
                        url,body,heads,writerCharset,readerCharset,fileNames);
        if (StringUtils.isBlank(readerCharset)) {
            readerCharset = "UTF-8";
        }
        HttpPost httppost = new HttpPost(url);
        httppost.setConfig(requestConfig);
        if (heads != null) {
            for (Map.Entry<String, Object> entry : heads.entrySet()) {
                httppost.setHeader(entry.getKey(), entry.getValue().toString());
            }
        }
        try {
            MultipartEntityBuilder builder = MultipartEntityBuilder.create();
            for(int i=0;i<fileNames.size();i++) {
                builder.addBinaryBody(fileNames.get(i), fileStreams.get(i), ContentType.MULTIPART_FORM_DATA, fileNames.get(i));
            }
            if (body != null) {
                for (Entry<String, String> entry : body.entrySet()) {
                    builder.addTextBody(entry.getKey(), entry.getValue());
                }
            }
            httppost.setEntity(builder.build());
            return doExecute(httppost, readerCharset,client);
        }catch (Exception e) {
            e.printStackTrace();
            return HttpClientRes.newFailure(ErrorEnum.failure, e.getMessage());
        }finally {
            try {
                for(int i=0;i<fileStreams.size();i++) {
                    fileStreams.get(i).close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
    /**
     * 执行请求-get/post
     * @param request
     * @param readerCharset
     * @return
     */
    private static HttpClientRes doExecute(final HttpUriRequest request,String readerCharset,CloseableHttpClient httpclient) {
        CloseableHttpResponse response = null;
        try {
            response = httpclient.execute(request);
            if (response.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
                return HttpClientRes.newFailure(ErrorEnum.failure, JSON.toJSONString(response.getStatusLine()));
            }

            HttpEntity resEntity = response.getEntity();
            if (resEntity != null) {
                return HttpClientRes.newSuccess(EntityUtils.toString(resEntity, readerCharset));
            }
            return HttpClientRes.newFailure(ErrorEnum.failure, "返回结果为空！");
        }catch (Exception e) {
            e.printStackTrace();
            return HttpClientRes.newFailure(ErrorEnum.failure, e.getMessage());
        } finally {
            if(response != null) {
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    return HttpClientRes.newFailure(ErrorEnum.failure, e.getMessage());
                }
            }
        }
    }
    
}
