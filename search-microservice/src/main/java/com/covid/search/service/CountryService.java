package com.covid.search.service;

import com.covid.search.model.RecoveredResponse;

public interface CountryService {
    RecoveredResponse getDataByCountry(String countryName);

    //CountryStatisticsResponse getCountryStatistics(String country);

    //String getCountryStatistics(String country);

    int getCountryTotal(String country, String from, String to);

    int getCountryDeaths(String country, String from, String to);

    int getCountryRecovered(String country, String from, String to);

    String getDailyStatistics(String country, String date);
}
