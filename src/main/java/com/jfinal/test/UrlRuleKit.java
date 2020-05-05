package com.jfinal.test;

/*
    author: Timor
    date: 2020/4/3 0003
*/
class UrlRuleKit {
    private static final String GET_URI_TAIL = "/get";
    private static final String ADD_URI_TAIL = "/add";
    private static final String UPDATE_URI_TAIL = "/update";
    private static final String DELETE_URI_TAIL = "/delete";
    private static final String LIST_URI_TAIL = "/list";

    private String baseUri;

    public UrlRuleKit(String baseUri) {
        this.baseUri = baseUri;
    }

    public String baseUri() {
        return baseUri;
    }

    public String getUri() {
        return baseUri + GET_URI_TAIL;
    }

    public String addUri() {
        return baseUri + ADD_URI_TAIL;
    }

    public String updateUri() {
        return baseUri + UPDATE_URI_TAIL;
    }

    public String deleteUri() {
        return baseUri + DELETE_URI_TAIL;
    }

    public String listUri() {
        return baseUri + LIST_URI_TAIL;
    }
}
