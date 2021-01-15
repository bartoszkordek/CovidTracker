package com.covid.search.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class CountryStatisticsResponse {

    private final String countryAbbreviation;
    private final List<DailyStatisticsResponse> results;

    public CountryStatisticsResponse(@JsonProperty("country") String countryAbbreviation,
                                     @JsonProperty("results") List results) {
        this.countryAbbreviation = countryAbbreviation;
        this.results = results;
    }

    public String getCountryAbbreviation() {
        return countryAbbreviation;
    }

    public List<DailyStatisticsResponse> getResults() {
        return results;
    }
}
