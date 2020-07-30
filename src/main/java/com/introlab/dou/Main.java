package com.introlab.dou;

import com.google.gson.Gson;
import com.introlab.dou.domain.Response;
import com.introlab.dou.domain.Vacancy;
import com.introlab.dou.parser.Parser;
import com.introlab.dou.parser.impl.DouParser;
import com.introlab.dou.scraper.Scraper;
import com.introlab.dou.scraper.impl.DouScraper;
import com.introlab.dou.util.JsoupUtil;
import com.introlab.dou.writer.Writer;
import com.introlab.dou.writer.impl.CsvWriter;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {

    private static final String VACANCIES_URL = "https://jobs.dou.ua/vacancies/xhr-load/?category=Java";
    private static final String BASE_URL = "https://jobs.dou.ua/vacancies/?category=Java";

    private static final String TOKEN_KEY = "csrfmiddlewaretoken";
    private static final String COUNT_KEY = "count";
    private static final String COOKIE_KEY = "csrftoken";

    private static final Map<String, String> PAYLOAD = new HashMap<String, String>() {{
        put(COUNT_KEY, "0");
    }};

    private static final Map<String, String> HEADERS = new HashMap<String, String>() {{
        put("Referer", "https://jobs.dou.ua/vacancies/?category=Java");
    }};

    private static final Gson gson = new Gson();
    private static final Scraper scraper = new DouScraper();
    private static final Parser parser = new DouParser();
    private static final Writer writer = new CsvWriter();

    public static void main(String[] args) throws IOException {
        List<Vacancy> list = new ArrayList<>();

        Response response;
        Document doc;

        getCsrfTokenAndCookie();

        do {
            response = gson.fromJson(scraper.postDownloadDocument(VACANCIES_URL, HEADERS, PAYLOAD), Response.class);
            doc = Jsoup.parse(response.getHtml());

            list.addAll(parser.parsePost(doc));

            if (!response.getLast()) {
                PAYLOAD.put(COUNT_KEY, String.valueOf(Integer.parseInt(PAYLOAD.get(COUNT_KEY)) + 40));
            }

        } while (!response.getLast());

        writer.export(list);
    }

    private static void getCsrfTokenAndCookie() {
        try {
            Connection.Response response = JsoupUtil.getResponse(BASE_URL);

            String token = StringUtils.substringBetween(response.body(), "window.CSRF_TOKEN = \"", "\";");
            String cookie = response.cookie(COOKIE_KEY);

            PAYLOAD.put(TOKEN_KEY, token);
            HEADERS.put("Cookie", String.join("=", COOKIE_KEY, cookie));
        } catch (IOException e) {
            throw new RuntimeException("Token not got.");
        }
    }

}
