package com.introlab.dou.domain;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Vacancy {

    private String title;
    private String companyName;
    private String description;
    private List<String> location = new ArrayList<>();

}
