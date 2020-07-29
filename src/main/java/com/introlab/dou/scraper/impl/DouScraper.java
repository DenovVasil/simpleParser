package com.introlab.dou.scraper.impl;

import com.introlab.dou.scraper.Scraper;
import com.introlab.dou.util.JsoupUtil;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class DouScraper implements Scraper {

    @Override
    public Document downloadDocument(String url) throws IOException {
        return JsoupUtil.get(url);
    }
}
