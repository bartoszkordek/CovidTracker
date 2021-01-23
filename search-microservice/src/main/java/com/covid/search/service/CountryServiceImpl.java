package com.covid.search.service;

import com.covid.search.data.Covid19ApiComServiceClient;
import com.covid.search.data.Covid19TrackingNarrativaServiceClient;
import com.covid.search.data.LocalCovidDataServiceClient;
import com.covid.search.model.CovidCountryResponse;
import com.covid.search.model.RecoveredResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CountryServiceImpl implements CountryService{

    private final Covid19ApiComServiceClient covid19ApiComServiceClient;
    private final Covid19TrackingNarrativaServiceClient covid19TrackingNarrativaServiceClient;
    private final LocalCovidDataServiceClient localCovidDataServiceClient;

    @Autowired
    public CountryServiceImpl(Covid19ApiComServiceClient covid19ApiComServiceClient,
                              Covid19TrackingNarrativaServiceClient covid19TrackingNarrativaServiceClient,
                              LocalCovidDataServiceClient localCovidDataServiceClient) {
        this.covid19ApiComServiceClient = covid19ApiComServiceClient;
        this.covid19TrackingNarrativaServiceClient = covid19TrackingNarrativaServiceClient;
        this.localCovidDataServiceClient = localCovidDataServiceClient;
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

        if (from == null && to == null) {
            int covid19TrackingNarrativaServiceResponse = covid19TrackingNarrativaServiceClient.getTotalCountry(country, from, to);
            return covid19TrackingNarrativaServiceResponse;
        } else {
            int localCovidDataServiceResponse = localCovidDataServiceClient.getTodayTotalTest();
            int covid19TrackingNarrativaServiceResponse = covid19TrackingNarrativaServiceClient.getTotalCountry(country, from, to);
            return (covid19TrackingNarrativaServiceResponse + localCovidDataServiceResponse)/2;
        }
    }

    @Override
    public int getCountryDeaths(String country, String from, String to) {
        return covid19TrackingNarrativaServiceClient.getDeathsCountry(country, from, to);
    }

    @Override
    public int getCountryRecovered(String country, String from, String to) {
        return covid19TrackingNarrativaServiceClient.getRecoveredCountry(country, from, to);
    }

    @Override
    public String getDailyStatistics(String country, String date) {
        return  covid19TrackingNarrativaServiceClient.getDailyStatistics(country, date);
    }
}
