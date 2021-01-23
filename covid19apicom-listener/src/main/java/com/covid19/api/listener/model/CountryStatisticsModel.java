package com.covid19.api.listener.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class CountryStatisticsModel {

    private final String countryAbbreviation;
    private final List<DailyStatisticsModel> results;

    public CountryStatisticsModel(@JsonProperty("country") String countryAbbreviation,
                                  @JsonProperty("results") List results) {
        this.countryAbbreviation = countryAbbreviation;
        this.results = results;
    }

    public String getCountryAbbreviation() {
        return countryAbbreviation;
    }

    public List<DailyStatisticsModel> getResults() {
        return results;
    }
}
