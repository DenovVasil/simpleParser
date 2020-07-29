package com.introlab.dou;

import com.google.gson.Gson;
import com.introlab.dou.parser.Parser;
import com.introlab.dou.parser.impl.DouParser;
import com.introlab.dou.scraper.Scraper;
import com.introlab.dou.scraper.impl.DouScraper;
import com.introlab.dou.util.JsoupUtil;
import com.introlab.dou.writer.Writer;
import com.introlab.dou.writer.impl.CsvWriter;
import org.apache.commons.text.StringEscapeUtils;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;


import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    private static final String URL = "https://jobs.dou.ua/vacancies/?category=Java";
    public static final String XHR_LOAD_CATEGORY_JAVA = "https://jobs.dou.ua/vacancies/xhr-load/?category=Java";
    //  private static final String URL_FOR_TOKEN = "https://jobs.dou.ua/";
    private static final String TOKEN_PATTERN = "input[name]";  // yt


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

/*
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
*/

        Document doc = Jsoup.connect(URL).get();
        Map<String, String> payload = new HashMap<>();
        //   payload.put("csrfmiddlewaretoken", doc.select("input[name=csrfmiddlewaretoken]").val());
        payload.put("csrfmiddlewaretoken", "KzVyeFs04PkB33BKpU8ectQ2ig5Xbj0OJr62NYK0Uc5UdxJEPCCFj6QLGvXqlFPn");

        payload.put("count", "100");


        Map<String, String> headers = new HashMap<>();
        headers.put("Cookie", "csrftoken=pOsIpMDtbRYMxFtwAjcJDOHc74G0mE2ooGDcY5Vt1eJ5H9Bq01GaKrHVvjytw0RX; _ga=GA1.2.2125210855.1595848999; _gid=GA1.2.296416754.1595848999; _gat=1");
        headers.put("Referer", "https://jobs.dou.ua/vacancies/?category=Java");
        //    headers.put("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");

        Writer w = new CsvWriter();

        Scraper scraper = new DouScraper();
        Document doc2 = scraper.postDownloadDocument(URL, headers, payload);

        Parser parser = new DouParser();
        w.export(parser.parse(doc2));


        payload.put("count", "140");
        doc2 = scraper.postDownloadDocument(XHR_LOAD_CATEGORY_JAVA, headers, payload);
        String normalizedHTML = StringEscapeUtils.unescapeHtml4(doc2.body().html());
        Response response = new Gson().fromJson(normalizedHTML, Response.class);
        w.export(parser.parse(doc2));


        // Parser parser = new DouParser();

        // parser.parse(scraper.downloadDocument())


    }
}
