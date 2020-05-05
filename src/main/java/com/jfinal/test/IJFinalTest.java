package com.jfinal.test;

import java.io.IOException;
import java.util.Map;

/*
    author: Timor
    date: 2020/3/29 0029
*/
public interface IJFinalTest {

    String get(String url) throws IOException;

    String get(String url, Map<String, String> queryParas) throws IOException;

    String get(String url, Map<String, String> queryParas, Map<String, String> headers) throws IOException;

    String post(String url, String data);

    String post(String url, String data, Map<String, String> headers);

    String post(String url, Map<String, String> queryParas, String data);

    String post(String url, Map<String, String> queryParas, String data, Map<String, String> headers);

    String put(String url);

    String delete(String url);
}
