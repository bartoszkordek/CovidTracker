package com.covid.search.service;

import com.covid.search.data.Covid19ApiComServiceClient;
import com.covid.search.data.Covid19TrackingNarrativaServiceClient;
import com.covid.search.data.LocalCovidDataServiceClient;
import com.covid.search.model.CovidCountryResponse;
import com.covid.search.model.RecoveredResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

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
            return covid19TrackingNarrativaServiceClient.getTotalCountry(country, from, to);
        } else {
            final CompletableFuture<Integer> firstServiceResult
                    = CompletableFuture.supplyAsync(localCovidDataServiceClient::getTodayTotalTest);
            final CompletableFuture<Integer> secondServiceResult
                    = CompletableFuture.supplyAsync(() -> covid19TrackingNarrativaServiceClient.getTotalCountry(country, from, to));
            int result = 0;
            int servicesAnswered = 0;
            try {
                result += firstServiceResult.get(10, TimeUnit.SECONDS);
                servicesAnswered++;
            } catch (InterruptedException | ExecutionException | TimeoutException e) {
                System.err.println("LocalCovidDataService unavailable");
            }
            try {
                result += secondServiceResult.get(10, TimeUnit.SECONDS);
                servicesAnswered++;
            } catch (InterruptedException | ExecutionException | TimeoutException e) {
                System.err.println("Covid19TrackingNarrativaService unavailable");
            }
            return servicesAnswered == 0 ? -1 : result / servicesAnswered;
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
