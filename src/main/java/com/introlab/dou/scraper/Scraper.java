package scraper;

import org.jsoup.nodes.Document;

import java.io.IOException;

public interface Scraper {

    Document downloadDocument(String url) throws IOException;
}
