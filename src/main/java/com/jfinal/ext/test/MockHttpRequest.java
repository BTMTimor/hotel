package com.jfinal.ext.test;

import com.google.common.collect.Maps;
import io.undertow.servlet.util.IteratorEnumeration;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.security.Principal;
import java.util.*;

public class MockHttpRequest  implements HttpServletRequest {
    private String method;
    private String uri;
    private Map<String, Object> attr = Maps.newHashMap();
    private Map<String, String> headers = new HashMap<>();
    private Map<String, String> parameters = Maps.newHashMap();

    private String requestBody;

    public MockHttpRequest() {}

    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -

    public MockHttpRequest setMethod(String method){
        this.method = method;
        return this;
    }

    public MockHttpRequest setUri(String uri){
        this.uri = uri;
        return this;
    }

    // 文件上传，post等……
    public MockHttpRequest setRequestBody(String requestBody){
        if(null != requestBody){
            this.requestBody = requestBody;
        }
        return this;
    }

    public MockHttpRequest setHeaders(Map<String, String> headers){
        this.headers = headers;
        return this;
    }

    public MockHttpRequest setParameters(Map<String, String> parameters) {
        this.parameters = parameters;
        return this;
    }

    public MockHttpRequest setParameter(String key, String val) {
        parameters.put(key, val);
        return this;
    }

    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -

    @Override
    public Object getAttribute(String key) {
        return attr.get(key);
    }

    @Override
    public Enumeration<String> getAttributeNames() {
        return new Hashtable<String, Object>(attr).keys();
    }

    @Override
    public ServletInputStream getInputStream() throws IOException {
        return new MockServletInputStream(requestBody);
    }

    @Override
    public String getCharacterEncoding() {
        return "UTF-8";
    }

    @Override
    public String getParameter(String key) {
        return parameters.get(key);
    }

    @Override
    public Map<String, String[]> getParameterMap() {
        Map<String, String[]> parameterMap = new HashMap<>();
        for (String key : parameters.keySet()) {
            parameterMap.put(key, new String[]{parameters.get(key)});
        }
        return parameterMap;
    }

    @Override
    public Enumeration<String> getParameterNames() {
        return new Hashtable<String, Object>(parameters).keys();
    }

    @Override
    public String[] getParameterValues(String key) {
        return new String[] { parameters.get(key) };
    }

    @Override
    public void setAttribute(String key, Object value) {
        attr.put(key, value);
    }

    @Override
    public RequestDispatcher getRequestDispatcher(final String view) {
        return new RequestDispatcher() {
            @Override
            public void forward(ServletRequest arg0, ServletResponse arg1) throws ServletException, IOException {
                System.out.println();
                System.out.println("JFinal view report -----------------------------------");
                System.out.println("forward to view :" + view);
                System.out.println("------------------------------------------------------");
            }

            @Override
            public void include(ServletRequest arg0, ServletResponse arg1) throws ServletException, IOException {

            }
        };
    }


    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -



    @Override
    public boolean authenticate(HttpServletResponse arg0) throws IOException, ServletException {return false;}

    @Override
    public AsyncContext getAsyncContext() {return null;}


    @Override
    public String getAuthType() {return null;}

    @Override
    public int getContentLength() {return 0;}

    @Override
    public long getContentLengthLong() {
        return 0;
    }

    @Override
    public String getContentType() {return null;}

    @Override
    public String getContextPath() {return null;}

    @Override
    public Cookie[] getCookies() {return null;}

    @Override
    public long getDateHeader(String arg0) {return 0;}

    @Override
    public DispatcherType getDispatcherType() {return null;}

    @Override
    public String getHeader(String name) {
        return headers.get(name);
    }

    @Override
    public Enumeration<String> getHeaderNames() {
        return new IteratorEnumeration<String>(headers.keySet().iterator());
    }

    @Override
    public Enumeration<String> getHeaders(String arg0) {
        return new IteratorEnumeration<String>(headers.values().iterator());
    }

    @Override
    public int getIntHeader(String arg0) {return 0;}

    @Override
    public String getLocalAddr() {return null;}

    @Override
    public Locale getLocale() {return null;}

    @Override
    public Enumeration<Locale> getLocales() {return null;}

    @Override
    public String getLocalName() {return null;}

    @Override
    public int getLocalPort() {return 0;}

    @Override
    public String getMethod() {
        return method;}

    @Override
    public Part getPart(String arg0) throws IOException, ServletException {return null;}

    @Override
    public <T extends HttpUpgradeHandler> T upgrade(Class<T> handlerClass) throws IOException, ServletException {
        return null;
    }

    @Override
    public Collection<Part> getParts() throws IOException, ServletException {return null;}

    @Override
    public String getPathInfo() {return null;}

    @Override
    public String getPathTranslated() {return null;}

    @Override
    public String getProtocol() {return null;}

    @Override
    public String getQueryString() {return null;}

    @Override
    public BufferedReader getReader() throws IOException {return null;}

    @Override
    public String getRealPath(String arg0) {return null;}

    @Override
    public String getRemoteAddr() {return null;}

    @Override
    public String getRemoteHost() {return null;}

    @Override
    public int getRemotePort() {return 0;}

    @Override
    public String getRemoteUser() {return null;}

    @Override
    public String getRequestedSessionId() {return null;}

    @Override
    public String getRequestURI() {
        return uri;
    }

    @Override
    public StringBuffer getRequestURL() {return null;}

    @Override
    public String getScheme() {return null;}

    @Override
    public String getServerName() {return null;}

    @Override
    public int getServerPort() {return 0;}

    @Override
    public ServletContext getServletContext() {return null;}

    @Override
    public String getServletPath() {return null;}

    @Override
    public HttpSession getSession() {return null;}

    @Override
    public String changeSessionId() {return null;}

    @Override
    public HttpSession getSession(boolean arg0) {return null;}

    @Override
    public Principal getUserPrincipal() {return null;}

    @Override
    public boolean isAsyncStarted() {return false;}

    @Override
    public boolean isAsyncSupported() {return false;}

    @Override
    public boolean isRequestedSessionIdFromCookie() {return false;}

    @Override
    public boolean isRequestedSessionIdFromUrl() {return false;}

    @Override
    public boolean isRequestedSessionIdFromURL() {return false;}

    @Override
    public boolean isRequestedSessionIdValid() {return false;}

    @Override
    public boolean isSecure() {return false;}

    @Override
    public boolean isUserInRole(String arg0) {return false;}

    @Override
    public void login(String arg0, String arg1) throws ServletException {}

    @Override
    public void logout() throws ServletException {}

    @Override
    public void removeAttribute(String key) {
        attr.remove(key);
    }

    @Override
    public void setCharacterEncoding(String arg0) {}

    @Override
    public AsyncContext startAsync() {return null;}

    @Override
    public AsyncContext startAsync(ServletRequest arg0, ServletResponse arg1) {return null;}
}
