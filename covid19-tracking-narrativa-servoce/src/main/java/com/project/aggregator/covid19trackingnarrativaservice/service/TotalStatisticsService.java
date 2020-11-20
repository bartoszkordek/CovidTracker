package com.project.aggregator.covid19trackingnarrativaservice.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.aggregator.covid19trackingnarrativaservice.exception.FutureDateException;
import com.project.aggregator.covid19trackingnarrativaservice.exception.NoFoundException;
import com.project.aggregator.covid19trackingnarrativaservice.model.TodayStatisticsModel;
import com.project.aggregator.covid19trackingnarrativaservice.pojo.TotalRoot;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class TotalStatisticsService {

    public TodayStatisticsModel getTodayTotal() throws JsonProcessingException, NoFoundException {
//int
        long currentDate = System.currentTimeMillis();
        SimpleDateFormat currentDateFormat =  new SimpleDateFormat("yyyy-MM-dd");
        String currentDateFormatted = currentDateFormat.format(currentDate);
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://api.covid19tracking.narrativa.com/api/"+currentDateFormatted+"/country/poland";
        HttpHeaders headers = new HttpHeaders();
        headers.set("Connection","keep-alive");
        HttpEntity<String> entity = new HttpEntity<String>(headers);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
        if(response.getStatusCode().is4xxClientError()){
            throw new NoFoundException("Cannot find response");
        }
        else {
            ObjectMapper objectMapper = new ObjectMapper();
            TotalRoot root = objectMapper.readValue(response.getBody(), TotalRoot.class);
            TodayStatisticsModel todayStatisticsModel = new TodayStatisticsModel(
                    root.getTotal().getTodayConfirmed(),
                    root.getTotal().getTodayDeaths(),
                    root.getTotal().getTodayRecovered(),
                    root.getTotal().getTodayNewConfirmed(),
                    root.getTotal().getTodayNewDeaths(),
                    root.getTotal().getTodayNewRecovered()
            );
            return todayStatisticsModel;
        }

    }

    public TodayStatisticsModel getTodayTotalCountry(String country) throws NoFoundException, JsonProcessingException {
        if (country.equalsIgnoreCase("poland")) {
            long currentDate = System.currentTimeMillis();
            SimpleDateFormat currentDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String currentDateFormatted = currentDateFormat.format(currentDate);
            RestTemplate restTemplate = new RestTemplate();
            String url = "https://api.covid19tracking.narrativa.com/api/" + currentDateFormatted + "/country/poland";
            HttpHeaders headers = new HttpHeaders();
            headers.set("Connection", "keep-alive");
            HttpEntity<String> entity = new HttpEntity<String>(headers);
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);

            if (response.getStatusCode().is4xxClientError()) {
                throw new NoFoundException("Cannot find response");
            } else {
                ObjectMapper objectMapper = new ObjectMapper();
                TotalRoot root = objectMapper.readValue(response.getBody(), TotalRoot.class);
                TodayStatisticsModel todayStatisticsModel = new TodayStatisticsModel(
                        root.getDates().getCurrentDate().getCountries().getPoland().getTodayConfirmed(),
                        root.getDates().getCurrentDate().getCountries().getPoland().getTodayDeaths(),
                        root.getDates().getCurrentDate().getCountries().getPoland().getTodayRecovered(),
                        root.getDates().getCurrentDate().getCountries().getPoland().getTodayNewConfirmed(),
                        root.getDates().getCurrentDate().getCountries().getPoland().getTodayNewDeaths(),
                        root.getDates().getCurrentDate().getCountries().getPoland().getTodayNewRecovered()
                );

                return todayStatisticsModel;
            }
        } else {
            throw new NoFoundException("Not found country");
        }

    }

    public TodayStatisticsModel getCountryDate(String country, String date) throws  JsonProcessingException, ParseException, FutureDateException {
        SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
        long current = System.currentTimeMillis();
        Date requestDate = dateFormatter.parse(date);
        String currentDateFormatted = dateFormatter.format(current);
        Date currentDate = dateFormatter.parse(currentDateFormatted);
        if(requestDate.after(currentDate)) {
            throw new FutureDateException("Future date");
        } else {
            RestTemplate restTemplate = new RestTemplate();
            String url = "https://api.covid19tracking.narrativa.com/api/" + date + "/country/"+country;
            HttpHeaders headers = new HttpHeaders();
            headers.set("Connection", "keep-alive");
            HttpEntity<String> entity = new HttpEntity<String>(headers);
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
            ObjectMapper objectMapper = new ObjectMapper();
            TotalRoot root = objectMapper.readValue(response.getBody(), TotalRoot.class);
            TodayStatisticsModel todayStatisticsModel = new TodayStatisticsModel(
                        root.getDates().getCurrentDate().getCountries().getPoland().getTodayConfirmed(),
                        root.getDates().getCurrentDate().getCountries().getPoland().getTodayDeaths(),
                        root.getDates().getCurrentDate().getCountries().getPoland().getTodayRecovered(),
                        root.getDates().getCurrentDate().getCountries().getPoland().getTodayNewConfirmed(),
                        root.getDates().getCurrentDate().getCountries().getPoland().getTodayNewDeaths(),
                        root.getDates().getCurrentDate().getCountries().getPoland().getTodayNewRecovered()
                );

                return todayStatisticsModel;
        }
    }
}
