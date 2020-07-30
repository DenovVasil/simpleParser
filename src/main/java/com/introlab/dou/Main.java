package com.introlab.dou;

import com.google.gson.Gson;
import com.introlab.dou.domain.Response;
import com.introlab.dou.domain.Vacancy;
import com.introlab.dou.parser.impl.DouParser;
import com.introlab.dou.scraper.Scraper;
import com.introlab.dou.scraper.impl.DouScraper;
import com.introlab.dou.writer.Writer;
import com.introlab.dou.writer.impl.CsvWriter;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;


import javax.lang.model.util.Elements;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {

    private static final String URL_POST = "https://jobs.dou.ua/vacancies/xhr-load/?category=Java";
    private static final String TOKEN_PATTERN = "input[name]";


    public static void main(String[] args) throws IOException {
        Gson gson = new Gson();
        Scraper scraper = new DouScraper();
        DouParser parser = new DouParser();
        Writer writer = new CsvWriter();
        List<Vacancy> list = new ArrayList<>();
        Response response;
        Document doc;

        int count = 0;
        Map<String, String> payload = new HashMap<>();
        //   Document token = Jsoup.connect(URL).get();
        //   payload.put("csrfmiddlewaretoken", token.select("input[name=csrfmiddlewaretoken]").val());          // возаращяет норм токен, но летит 403
        payload.put("csrfmiddlewaretoken", "gKPdEyFEVZPmjU3xLAB8lspEULHKaK5VfC0HdRXELmAFtobrbi5zs5pni0zdk6Uu"); // а с хардкодом все гуд
        payload.put("count", String.valueOf(count));

        Map<String, String> headers = new HashMap<>();
        headers.put("Cookie", "csrftoken=pOsIpMDtbRYMxFtwAjcJDOHc74G0mE2ooGDcY5Vt1eJ5H9Bq01GaKrHVvjytw0RX; _ga=GA1.2.2125210855.1595848999; _gid=GA1.2.296416754.1595848999; _gat=1");
        headers.put("Referer", "https://jobs.dou.ua/vacancies/?category=Java");

        do {
            response = gson.fromJson(scraper.postDownloadDocument(URL_POST, headers, payload), Response.class);
            doc = Jsoup.parse(response.getHtml());
            list.addAll(parser.parsePost(doc));
            if (!response.getLast()) {
                payload.put("count", String.valueOf(count += 40));
            }

        } while (!response.getLast());

        writer.export(list);

    }

}
