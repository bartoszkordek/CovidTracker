package com.covid19.api.listener.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DailyStatisticsModel {

    private final String date;
    private final int newInfected;
    private final int newDeaths;
    private final int newRecovered;

    public DailyStatisticsModel(@JsonProperty("date") String date,
                                @JsonProperty("newInfected") int newInfected,
                                @JsonProperty("newDeaths") int newDeaths,
                                @JsonProperty("newRecovered") int newRecovered) {
        this.date = date;
        this.newInfected = newInfected;
        this.newDeaths = newDeaths;
        this.newRecovered = newRecovered;
    }

    public String getDate() {return date;}

    public int getNewInfected() {
        return newInfected;
    }

    public int getNewDeaths() {
        return newDeaths;
    }

    public int getNewRecovered() {
        return newRecovered;
    }
}
