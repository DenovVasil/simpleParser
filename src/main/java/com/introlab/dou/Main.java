package com.introlab.dou;

import com.introlab.dou.parser.Parser;
import com.introlab.dou.parser.impl.DouParser;
import com.introlab.dou.scraper.Scraper;
import com.introlab.dou.scraper.impl.DouScraper;
import com.introlab.dou.util.JsoupUtil;
import com.introlab.dou.writer.Writer;
import com.introlab.dou.writer.impl.CsvWriter;
import org.jsoup.Connection;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Main {

    private static final String URL = "https://jobs.dou.ua/vacancies/?category=Java";

    public static void main(String[] args) throws IOException {
//        Scraper scraper = new DouScraper();
//        Parser parser = new DouParser();
//        Writer writer = new CsvWriter();
//
//        writer.export(
//                parser.parse(
//                        scraper.downloadDocument(URL)
//                )
//        );

        Map<String, String> headers = new HashMap<>();
        headers.put("Cookie", "csrftoken=pOsIpMDtbRYMxFtwAjcJDOHc74G0mE2ooGDcY5Vt1eJ5H9Bq01GaKrHVvjytw0RX; _ga=GA1.2.2125210855.1595848999; _gid=GA1.2.296416754.1595848999");
        headers.put("Referer", "https://jobs.dou.ua/vacancies/?category=Java");

        Map<String, String> payload = new HashMap<>();
        payload.put("csrfmiddlewaretoken", "e0kpnLiuyA2Pj4z5EDXNAjKJK0keBdpjdSvTW4AuoXN8tyHZ4lreHWKs8fcHLzeS");
        payload.put("count", "20");

        String response = JsoupUtil.post(
                "https://jobs.dou.ua/vacancies/xhr-load/?category=Java",
                    headers, payload).body();
        System.out.println(response);
    }
}
