package com.covid.search.service;

import com.covid.search.model.RecoveredResponse;

public interface CountryService {
    RecoveredResponse getDataByCountry(String countryName);
}
