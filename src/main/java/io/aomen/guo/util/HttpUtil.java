package io.aomen.guo.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;

import javax.net.ssl.SSLSession;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.AuthCache;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.ClientContext;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.BasicAuthCache;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HTTP;

import com.fasterxml.jackson.databind.JsonNode;
import com.sun.net.ssl.HostnameVerifier;
import com.sun.net.ssl.HttpsURLConnection;

/**
 *
 * 调用itil工具类
 */
public class HttpUtil {

    private String itil_url = "www.baidu.com"; // 被调用接口的url
    private String itil_usernmae = ""; // 用户名
    private String itil_passWorld = ""; // 密码
    private int itil_port = -1; // 端口号
    private String itil_protocol = "https";// 协议

    public HttpUtil() {

    }

    /**
     * rest post 方式调用itil接口
     * 
     * @param JsonParams
     *            json格式的请求参数
     * @param apiUrl
     *            api接口地址
     * @return
     * @throws Exception
     */
    public JsonNode httpRequestPost(String JsonParams, String apiUrl) throws Exception {

        HttpHost targetHost = new HttpHost(itil_url, itil_port, itil_protocol);

        DefaultHttpClient httpclient = new DefaultHttpClient();

        int SO_TIMEOUT = 60 * 1000;// 设置等待数据超过时间为60秒，这里是连上别人的接口，但是迟迟不返回数据（堵塞的异常处理）
        HttpParams params = new BasicHttpParams();
        params.setIntParameter(CoreConnectionPNames.SO_TIMEOUT, SO_TIMEOUT);
        httpclient.setDefaultHttpParams(params);

        // 此用户名和密码上生产前需要修改为自己的账户
        httpclient.getCredentialsProvider().setCredentials(new AuthScope(targetHost.getHostName(), targetHost.getPort()), new UsernamePasswordCredentials(itil_usernmae, itil_passWorld));

        AuthCache authCache = new BasicAuthCache();
        BasicScheme basicAuth = new BasicScheme();
        authCache.put(targetHost, basicAuth);

        BasicHttpContext localcontext = new BasicHttpContext();
        localcontext.setAttribute(ClientContext.AUTH_CACHE, authCache);
        String url = itil_protocol + "://" + itil_url + ":" + itil_port + apiUrl;

        HttpPost httpPost = new HttpPost(url);

        StringEntity stringEntity = new StringEntity(JsonParams, HTTP.UTF_8);

        httpPost.setHeader("Content-Type", "application/json");
        httpPost.setHeader("accept", "application/json");

        httpPost.setEntity(stringEntity);

        HttpResponse response = httpclient.execute(targetHost, httpPost, localcontext);

        HttpEntity entity = response.getEntity();

        System.out.println("StatusCode:" + response.getStatusLine().getStatusCode());

        BufferedReader reader = new BufferedReader(new InputStreamReader(entity.getContent(), "utf-8"));// 防止乱码
        String buffer = "";
        StringBuffer sb = new StringBuffer();
        while ((buffer = reader.readLine()) != null) {
            sb.append(buffer);
        }
        reader.close();
        httpPost.releaseConnection();
        System.out.println("entity:" + sb.toString());
        httpclient.getConnectionManager().shutdown();

        JsonNode json = JacksonUtil.getObjectMapper().readTree(sb.toString());
        return json;

    }

    /**
     * rest get 方式调用itil接口
     * 
     * @param apiUrl
     *            api接口地址,参数直接拼在地址后面
     * @return json字符串格式的返回结果 其中ReturnCode为0表示请求成功
     * @throws Exception
     */
    public JsonNode httpRequestGet(HashMap<String, Object> content, String apiUrl) throws Exception {

        HttpHost targetHost = new HttpHost(itil_url, itil_port, itil_protocol);

        DefaultHttpClient httpclient = new DefaultHttpClient();

        // 此用户名和密码上生产前需要修改为自己的账户
        httpclient.getCredentialsProvider().setCredentials(new AuthScope(targetHost.getHostName(), targetHost.getPort()), new UsernamePasswordCredentials(itil_usernmae, itil_passWorld));

        AuthCache authCache = new BasicAuthCache();
        BasicScheme basicAuth = new BasicScheme();
        authCache.put(targetHost, basicAuth);

        BasicHttpContext localcontext = new BasicHttpContext();
        localcontext.setAttribute(ClientContext.AUTH_CACHE, authCache);
        String url =  itil_protocol + "://" + itil_url + ":" + itil_port +  apiUrl;

        HttpGet httpGet = new HttpGet(url);

        httpGet.setHeader("Content-Type", "application/json");
        httpGet.setHeader("accept", "application/json");

/*        trustAllHttpsCertificates();  
        HttpsURLConnection.setDefaultHostnameVerifier(hv);  
*/        HttpResponse response = httpclient.execute(targetHost, httpGet, localcontext);

        HttpEntity entity = response.getEntity();

        System.out.println("StatusCode:" + response.getStatusLine().getStatusCode());

        BufferedReader reader = new BufferedReader(new InputStreamReader(entity.getContent()));
        String buffer = "";
        StringBuffer sb = new StringBuffer();
        while ((buffer = reader.readLine()) != null) {
            sb.append(buffer);
        }
        reader.close();
        httpGet.releaseConnection();
        System.out.println("entity:" + sb.toString());
        httpclient.getConnectionManager().shutdown();

        JsonNode json = JacksonUtil.getObjectMapper().readTree(sb.toString());
        return json;

    }

    public static void main(String[] args) throws Exception {

        String apiUrl = "";// 调用方法对应的URL

        JsonNode jsonNode = new HttpUtil().httpRequestGet(new HashMap<String, Object>(), apiUrl);
        System.err.println(jsonNode.toString());
    }


}
