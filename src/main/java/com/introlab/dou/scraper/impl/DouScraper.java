package scraper.impl;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import scraper.Scraper;

import java.io.IOException;

public class DouScraper implements Scraper {

    @Override
    public Document downloadDocument(String url) throws IOException {
        return Jsoup.connect(url).get();
    }
}
