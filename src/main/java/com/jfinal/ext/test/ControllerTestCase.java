package com.jfinal.ext.test;
import com.google.common.base.Charsets;
import com.jfinal.core.JFinalKit;
import com.jfinal.handler.Handler;
import com.jfinal.log.Log;
import org.apache.commons.io.FileUtils;

import javax.servlet.ServletContext;
import java.io.File;
import java.io.IOException;

public class ControllerTestCase {
    protected static final Log LOG = Log.getLog(ControllerTestCase.class);
    protected static ServletContext servletContext = new MockServletContext();;
    protected MockHttpRequest request;
    protected MockHttpResponse response;
    protected Handler handler;

    protected String actionUrl;
    protected String bodyData;

    public ControllerTestCase() {
        handler = JFinalKit.getHandlers().getActionHandler();
        request = new MockHttpRequest();
        response = new MockHttpResponse();
    }

    public Object findAttrAfterInvoke(String key) {
        return request.getAttribute(key);
    }

    private String getTarget(String url, MockHttpRequest request) {
        String target = url;
        if (url.contains("?")) {
            target = url.substring(0, url.indexOf("?"));
            String queryString = url.substring(url.indexOf("?") + 1);
            String[] keyVals = queryString.split("&");
            for (String keyVal : keyVals) {
                int i = keyVal.indexOf('=');
                String key = keyVal.substring(0, i);
                String val = keyVal.substring(i + 1);
                request.setParameter(key, val);
            }
        }
        return target;
    }

    public String invoke() {
        handler.handle(getTarget(actionUrl, request), request, response, new boolean[] { true });
        return response.getResponseBody();
    }

    public ControllerTestCase post(File bodyFile) throws IOException {
        this.bodyData = FileUtils.readFileToString(bodyFile, Charsets.UTF_8);
        return this;
    }

    public ControllerTestCase post(String bodyData) {
        this.bodyData = bodyData;
        return this;
    }

    public ControllerTestCase use(String actionUrl) {
        this.actionUrl = actionUrl;
        return this;
    }

    public ControllerTestCase writeTo(File responseFile) {
        return this;
    }

}
