package com.covid19.api.listener.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TodayStatisticsModel {

    private final int totalInfected;
    private final int totalDeaths;
    private final int totalRecovered;
    private final int newInfected;
    private final int newDeaths;
    private final int newRecovered;

    public TodayStatisticsModel(@JsonProperty("totalInfected") int totalInfected,
                                @JsonProperty("totalDeaths") int totalDeaths,
                                @JsonProperty("totalRecovered") int totalRecovered,
                                @JsonProperty("newInfected") int newInfected,
                                @JsonProperty("newDeaths") int newDeaths,
                                @JsonProperty("newRecovered") int newRecovered) {
        this.totalInfected = totalInfected;
        this.totalDeaths = totalDeaths;
        this.totalRecovered = totalRecovered;
        this.newInfected = newInfected;
        this.newDeaths = newDeaths;
        this.newRecovered = newRecovered;
    }

    public int getTotalInfected() {
        return totalInfected;
    }

    public int getTotalDeaths() {
        return totalDeaths;
    }

    public int getTotalRecovered() {
        return totalRecovered;
    }

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
