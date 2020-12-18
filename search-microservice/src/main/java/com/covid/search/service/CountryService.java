package com.covid.search.service;

import com.covid.search.model.RecoveredResponse;

public interface CountryService {
    RecoveredResponse getDataByCountry(String countryName);

    int getCountryTotal(String country, String from, String to);

    int getCountryDeaths(String country, String from, String to);
}
