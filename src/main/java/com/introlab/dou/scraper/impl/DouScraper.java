package com.introlab.dou.scraper.impl;

import com.introlab.dou.scraper.Scraper;
import com.introlab.dou.util.JsoupUtil;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.Map;

public class DouScraper implements Scraper {

    @Override
    public Document getDownloadDocument(String url) throws IOException {
        return JsoupUtil.get(url);
    }

    @Override
    public Document postDownloadDocument(String url, Map<String, String> headers, Map<String, String> payload) throws IOException {
        return JsoupUtil.post(url, headers, payload);
    }
}
