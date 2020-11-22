package com.project.aggregator.covid19trackingnarrativaservice.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Country{

    private String id;
    private List<Link> links;
    private String name;

    @JsonProperty("name_es")
    private String nameEs;

    @JsonProperty("name_it")
    private String nameIt;

    public String getId() {
        return id;
    }

    public List<Link> getLinks() {
        return links;
    }

    public String getName() {
        return name;
    }

    public String getNameEs() {
        return nameEs;
    }

    public String getNameIt() {
        return nameIt;
    }
}
