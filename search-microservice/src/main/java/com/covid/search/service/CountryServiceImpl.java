package com.covid.search.service;

import com.covid.search.data.Covid19ApiComServiceClient;
import com.covid.search.model.RecoveredResponse;
import com.covid19.api.listener.model.CovidCountryResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CountryServiceImpl implements CountryService{

    private final Covid19ApiComServiceClient covid19ApiComServiceClient;

    @Autowired
    public CountryServiceImpl(Covid19ApiComServiceClient covid19ApiComServiceClient) {
        this.covid19ApiComServiceClient = covid19ApiComServiceClient;
    }

    @Override
    public RecoveredResponse getDataByCountry(String countryName) {

        RecoveredResponse tempResponse=new RecoveredResponse();

        CovidCountryResponse covidResponse=covid19ApiComServiceClient
                .getCountryInfo(countryName);

        tempResponse.setCountry(covidResponse.getCountry());
        tempResponse.setCountryCode(covidResponse.getCountryCode());
        tempResponse.setDate(covidResponse.getDate());
        tempResponse.setRecovered(covidResponse.getRecovered());

        return tempResponse;
    }
}
