package com.introlab.dou.scraper;

import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.Map;

public interface Scraper {

    Document getDownloadDocument(String url) throws IOException;
    Document postDownloadDocument( String url, Map<String, String> headers, Map<String, String> payload) throws IOException;

}
