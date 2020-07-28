import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

public class ParserDOU {

    private static final String HTTP = "https://jobs.dou.ua/vacancies/?category=Java";
    private static final String ELEMENTS_LIST_PATH = "div.l-items ul.lt li.l-vacancy";
    private static final String ELEMENT_PATTERN = "div.vacancy div.title ";


    public List<Vacancy> generateVacanciesList() throws Exception {
        List<Vacancy> vacanciesList = new ArrayList<>();
        Document doc = Jsoup.connect(HTTP).get();
        Elements list = doc.select(ELEMENTS_LIST_PATH);

        for (Element element : list) {
            Vacancy vacancy = new Vacancy();

            vacancy.setTitle(element.select(ELEMENT_PATTERN + "a.vt").text());
            vacancy.setCompanyName(element.select(ELEMENT_PATTERN + "strong a.company").text());
            vacancy.setDescription(element.select(ELEMENT_PATTERN.split(" ")[0] + " div.sh-info").text());

            for (String str : element.select(ELEMENT_PATTERN + "span.cities").text().split(", ")) {
                vacancy.getLocation().add(str);
            }
            vacanciesList.add(vacancy);
        }
        return vacanciesList;

    }



}