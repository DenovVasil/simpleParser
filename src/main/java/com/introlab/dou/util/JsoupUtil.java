package com.introlab.dou.util;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.Map;

public class JsoupUtil {

    public static Document get(String url) throws IOException {
        return Jsoup.connect(url).get();
    }

    public static Connection.Response postResponse(String url, Map<String, String> headers, Map<String, String> payload) throws IOException {
        return Jsoup.connect(url)
                .ignoreContentType(true)
                .headers(headers)
                .data(payload)
                .method(Connection.Method.POST)
                .execute();
    }

    public static Document post( String url, Map<String, String> headers,  Map<String, String> payload) throws IOException{
        return Jsoup.connect(url)
                .headers(headers)
                .data(payload)
                .ignoreContentType(true)
                .timeout(3000)
                .post();

    }
}
