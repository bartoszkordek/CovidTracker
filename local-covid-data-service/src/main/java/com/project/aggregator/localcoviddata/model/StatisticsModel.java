package com.project.aggregator.localcoviddata.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class StatisticsModel {

    private final int newInfected;
    private final int newDeaths;

    public StatisticsModel(@JsonProperty("newInfected") int newInfected,
                           @JsonProperty("newDeaths") int newDeaths) {
        this.newInfected = newInfected;
        this.newDeaths = newDeaths;
    }

    public int getNewInfected() {
        return newInfected;
    }

    public int getNewDeaths() {
        return newDeaths;
    }

}
