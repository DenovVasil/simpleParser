package com.introlab.dou.parser.impl;

import com.introlab.dou.domain.Vacancy;
import com.introlab.dou.parser.Parser;
import org.jsoup.helper.StringUtil;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

public class DouParser implements Parser {

    private static final String ELEMENTS_LIST_PATH = "div.l-items ul.lt li.l-vacancy";
    private static final String ELEMENT_PATTERN = "div.vacancy div.title ";

    @Override
    public List<Vacancy> parse(Document document) {
        List<Vacancy> vacanciesList = new ArrayList<>();
        Elements list = document.select(ELEMENTS_LIST_PATH);

       return getVacancyField(list);
    }

    public List<Vacancy>parsePost(Document document){
        List<Vacancy> vacanciesList = new ArrayList<>();
        Elements list = document.select("li.l-vacancy");

        return getVacancyField(list);
    }

    private List<Vacancy> getVacancyField(Elements list){
        List<Vacancy> vacanciesList = new ArrayList<>();


        for(Element element : list){
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
