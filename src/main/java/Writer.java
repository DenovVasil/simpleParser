import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;


public class Writer {

    String[] HEADERS = {"\"title\"", "\"company_name\"", "\"description\"", "\"location\""};

    public void createCSVFile(List<Vacancy> vacancies ) throws IOException {
        FileWriter out = new FileWriter("src/main/java/resources/vacancy.csv");

        try (CSVPrinter printer = new CSVPrinter(out, CSVFormat.DEFAULT.withHeader(HEADERS))) {
            vacancies.forEach(vacancy -> {
                try {
                    printer.printRecord(vacancy.getTitle(), vacancy.getCompanyName(),
                             vacancy.getDescription(), vacancy.getLocation());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }

    }

    public static void main(String[] args) throws Exception{
        Writer w = new Writer();

        w.createCSVFile(new ParserDOU().generateVacanciesList());
    }

}