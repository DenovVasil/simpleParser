package com.introlab.dou.writer;

import com.introlab.dou.domain.Vacancy;

import java.io.IOException;
import java.util.List;

public interface Writer {

    boolean export(List<Vacancy> vacancies) throws IOException;
}
