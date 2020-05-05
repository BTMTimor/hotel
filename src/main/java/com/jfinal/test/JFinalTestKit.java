package com.jfinal.test;

import com.jfinal.core.JFinalKit;
import com.jfinal.ext.test.MockHttpRequest;
import com.jfinal.ext.test.MockHttpResponse;
import com.jfinal.handler.Handler;
import com.jfinal.kit.HttpKit;
import io.jboot.app.JbootApplication;
import lombok.SneakyThrows;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/*
    author: Timor
    date: 2020/3/29 0029
*/
public class JFinalTestKit {
    private static final boolean DEV_MODE = true;
    private static final String DEFAULT_TEST_URL = "http://localhost:8080/api/v1/test/debug";

    private static Handler handler;
    private static boolean isInit = false;
    private static boolean isEnvInit = true;

    private MockHttpRequest request;
    private MockHttpResponse response;
    private String testUrl;

    public JFinalTestKit() {
        this(new MockHttpRequest(), new MockHttpResponse());
    }

    public JFinalTestKit(String testUrl) {
        this(testUrl, new MockHttpRequest(), new MockHttpResponse());
    }

    public JFinalTestKit(MockHttpRequest request, MockHttpResponse response) {
        this(DEFAULT_TEST_URL, request, response);
    }

    public JFinalTestKit(String testUrl, MockHttpRequest request, MockHttpResponse response) {
        this.request = request;
        this.response = response;
        this.testUrl = testUrl;

        if(!isInit) init();
    }

    public void logInfo(Object info){
        if(DEV_MODE){
            System.out.println("[info] " + info);
        }
    }

    @SneakyThrows
    private void init(){
        logInfo("jfinal test init...");
        if (!isEnvInit){
            isEnvInit = true;
            logInfo("start jboot...");
            JbootApplication.run(new String[]{});

            // 一定要先请求一下，不然后端没有真正的初始化
            HttpKit.get(testUrl);
        }
        // 不可能为空，所以不校验，一定要先启动jfinal
        if(null == handler){
            logInfo("init handle...");
            handler = JFinalKit.getHandlers().getActionHandler();
            if(null == handler){
                throw new Exception("[error] get handel failure!");
            }
        }

        isInit = true;
        logInfo("jfinal test init success.");
    }



    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -

    private String getTargetUri(String url){
        if(url.contains("?")){
            return url.substring(0, url.indexOf("?"));
        }
        return url;
    }

    private String getMethod(String uri){
        int index = uri.lastIndexOf("/");
        if(index == uri.length()-1){
            return "index";
        }else if(index > 0){
            return uri.substring(index);
        }
        return uri;
    }

    private String getQueryString(Map<String, String> queryParameters){
        StringBuilder sb = new StringBuilder();
        for (String key : queryParameters.keySet()) {
            sb.append(key).append("=").append(queryParameters.get(key));
        }
        return sb.toString();
    }

    private Map<String, Object> parseParasToMap(String url){
        Map<String, Object> paras = new HashMap<>();
        String queryString = url;
        if (url.contains("?")) {
            queryString = url.substring(url.indexOf("?") + 1);
        }

        String[] keyVals = queryString.split("&");
        for (String keyVal : keyVals) {
            int i = keyVal.indexOf('=');
            if(i > 0){
                String key = keyVal.substring(0, i);
                String val = keyVal.substring(i + 1);
                paras.put(key, val);
            }
        }
        return paras;
    }

    private void report(String method, String uri, Map<?, ?> data, Map<String, String> headers) {
        System.out.println("\nController Test report -------- " + new Date() + " --------------------------");
        System.out.println("Url         : " + method + " " + uri);
        if(null != headers && !headers.isEmpty()) System.out.println("headers     : " + headers);
        if(null != data && !data.isEmpty()) System.out.println("parameters  : " + data);
        System.out.println("--------------------------------------------------------------------------------");
    }

    private String invoke(String method, String uri, Map<String, Object> data,
            Map<String, String> headers) throws Exception {
        if(null == uri){
            throw new Exception("uri can't be null");
        }
        if(null == headers){
            throw new Exception("headers can't be null");
        }
        if(null == data){
            throw new Exception("data can't be null");
        }

        // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
        // 不信任提供的uri，处理下
        uri = getTargetUri(uri);
        data.putAll(parseParasToMap(uri));
        Map<String, String> map = processPara(data);
        // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -

        request.setMethod(method)
            .setUri(uri)
            .setParameters(map)
            .setHeaders(headers);
        response.init();

        // if(DEV_MODE) report(method, uri, map, headers);
        handler.handle(uri, request, response, new boolean[] {false});

        return response.getResponseBody();
    }

    private Map<String, String> processPara(Map<String, Object> data) {
        Map<String, String> result = new HashMap<>();
        for (String key : data.keySet()) {
            result.put(key, String.valueOf(data.get(key)));
        }
        return result;
    }

    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
    public String get(String url) {
        return this.get(url, "");
    }

    public String get(String url, String queryParas) {
        return this.get(url, parseParasToMap(queryParas));
    }

    public String get(String url, Map<String, Object> queryParas) {
        return this.get(url, queryParas, new HashMap<>());
    }

    @SneakyThrows
    public String get(String url, Map<String, Object> queryParas, Map<String, String> headers) {
        return this.invoke("GET", url, queryParas, headers);
    }



    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
    public String post(String url) {
        return this.post(url,"");
    }

    public String post(String url, String data) {
        return this.post(url, parseParasToMap(data));
    }

    public String post(String url, Map<String, Object> data) {
        return this.post(url, data, new HashMap<>());
    }

    @SneakyThrows
    public String post(String url, Map<String, Object> postData, Map<String, String> headers) {
        return this.invoke("POST", url, postData, headers);
    }


    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -

    public String put(String url) {
        return this.post(url,"");
    }

    public String put(String url, String data) {
        return this.post(url, parseParasToMap(data));
    }

    public String put(String url, Map<String, Object> data) {
        return this.post(url, data, new HashMap<>());
    }

    @SneakyThrows
    public String put(String url, Map<String, Object> postData, Map<String, String> headers) {
        return this.invoke("PUT", url, postData, headers);
    }


    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
    public String delete(String url) {
        return this.post(url,"");
    }

    public String delete(String url, String data) {
        return this.post(url, parseParasToMap(data));
    }

    public String delete(String url, Map<String, Object> data) {
        return this.post(url, data, new HashMap<>());
    }

    @SneakyThrows
    public String delete(String url, Map<String, Object> postData, Map<String, String> headers) {
        return this.invoke("DELETE", url, postData, headers);
    }



    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -

    public MockHttpRequest getRequest() {
        return request;
    }

    public MockHttpResponse getResponse() {
        return response;
    }
}
