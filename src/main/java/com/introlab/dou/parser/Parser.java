package com.introlab.dou.parser;

import com.introlab.dou.domain.Vacancy;
import org.jsoup.nodes.Document;

import java.util.List;

public interface Parser {

    List<Vacancy> parse(Document document);
}
