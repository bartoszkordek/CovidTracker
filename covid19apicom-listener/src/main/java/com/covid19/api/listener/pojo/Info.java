package com.covid19.api.listener.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Info{
    private String date;

    @JsonProperty("date_generation")
    private String dateGeneration;

    private String yesterday;

    public String getDate() {
        return date;
    }

    public String getDateGeneration() {
        return dateGeneration;
    }

    public String getYesterday() {
        return yesterday;
    }
}
