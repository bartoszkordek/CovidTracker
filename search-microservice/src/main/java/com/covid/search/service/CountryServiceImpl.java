package com.covid.search.service;

import com.covid.search.data.Covid19ApiComServiceClient;
import com.covid.search.data.Covid19TrackingNarrativaServiceClient;
import com.covid.search.model.CovidCountryResponse;
import com.covid.search.model.RecoveredResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CountryServiceImpl implements CountryService{

    private final Covid19ApiComServiceClient covid19ApiComServiceClient;
    private final Covid19TrackingNarrativaServiceClient covid19TrackingNarrativaServiceClient;

    @Autowired
    public CountryServiceImpl(Covid19ApiComServiceClient covid19ApiComServiceClient,
                              Covid19TrackingNarrativaServiceClient covid19TrackingNarrativaServiceClient) {
        this.covid19ApiComServiceClient = covid19ApiComServiceClient;
        this.covid19TrackingNarrativaServiceClient = covid19TrackingNarrativaServiceClient;
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

    @Override
    public int getCountryTotal(String country, String from, String to) {
        return covid19TrackingNarrativaServiceClient.getTotalCountry(country, from, to);
    }

    @Override
    public int getCountryDeaths(String country, String from, String to) {
        return covid19TrackingNarrativaServiceClient.getDeathsCountry(country, from, to);
    }
}
