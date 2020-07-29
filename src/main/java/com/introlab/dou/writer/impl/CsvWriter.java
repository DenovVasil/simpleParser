package com.introlab.dou.writer.impl;

import com.introlab.dou.domain.Vacancy;
import com.introlab.dou.writer.Writer;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class CsvWriter implements Writer {

    String[] HEADERS = {"\"title\"", "\"company_name\"", "\"description\"", "\"location\""};
    static int count = 1;

    @Override
    public boolean export(List<Vacancy> vacancies) throws IOException {

        FileWriter out = new FileWriter("/home/user/Desktop/firstTask/simpleParser/src/resources/vacancy" + count + ".csv");
        count++;
        try (CSVPrinter printer = new CSVPrinter(out, CSVFormat.DEFAULT.withHeader(HEADERS))) {
            vacancies.forEach(vacancy -> {
                try {
                    printer.printRecord(vacancy.getTitle(), vacancy.getCompanyName(),
                            vacancy.getDescription(), vacancy.getLocation());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
