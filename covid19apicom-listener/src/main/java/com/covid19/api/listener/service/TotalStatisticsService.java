package com.covid19.api.listener.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.covid19.api.listener.exception.FromDateAfterToDateException;
import com.covid19.api.listener.exception.FutureDateException;
import com.covid19.api.listener.exception.NoFoundException;
import com.covid19.api.listener.map.CountryMap;
import com.covid19.api.listener.model.DailyStatisticsModel;
import com.covid19.api.listener.model.TodayStatisticsModel;
import com.covid19.api.listener.pojo.TotalRoot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@Service
public class TotalStatisticsService {

    @Autowired
    private Environment environment;

    public TodayStatisticsModel getTodayTotal() throws JsonProcessingException, NoFoundException {

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

    public int getCountryTotal(String requestCountry, String from, String to) throws JsonProcessingException, ParseException, FromDateAfterToDateException, FutureDateException {

        CountryMap countryMap = new CountryMap();
        String country = countryMap.getCountries().get(requestCountry);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date currentDate = new Date(System.currentTimeMillis());
        String currentDateFormatted = format.format( currentDate );
        RestTemplate restTemplate = new RestTemplate();

        if(from==null || to==null ){
            StringBuilder url = new StringBuilder();
            url.append(environment.getProperty("microservice.listen.api"))
                    .append(currentDateFormatted)
                    .append("/country/")
                    .append(country);

            HttpHeaders headers = new HttpHeaders();
            headers.set("Connection", "keep-alive");
            HttpEntity<String> entity = new HttpEntity<String>(headers);
            ResponseEntity<String> response = restTemplate.exchange(url.toString(), HttpMethod.GET, entity, String.class);
            ObjectMapper objectMapper = new ObjectMapper();
            TotalRoot root = objectMapper.readValue(response.getBody(), TotalRoot.class);

            int todayTotal = root.getDates().getCurrentDate().getCountries().getPoland().getTodayConfirmed();

            return todayTotal;

        } else{

            Date fromDate = new SimpleDateFormat("yyyy-MM-dd").parse(from);
            Date toDate = new SimpleDateFormat("yyyy-MM-dd").parse(to);

            long diffInMilliesFromTo = toDate.getTime() - fromDate.getTime();
            long datesDiffFromTo = TimeUnit.DAYS.convert(diffInMilliesFromTo, TimeUnit.MILLISECONDS);
            if(datesDiffFromTo<0) throw new FromDateAfterToDateException("From date after to date");

            long diffInMilliesFromCurrent = currentDate.getTime() - fromDate.getTime();
            long datesDiffFromCurrent = TimeUnit.DAYS.convert(diffInMilliesFromCurrent, TimeUnit.MILLISECONDS);
            if(datesDiffFromCurrent<0) throw new FutureDateException("From date after current date");

            long diffInMilliesCurrentTo =  currentDate.getTime() - toDate.getTime();
            long datesDiffFromCurrentTo = TimeUnit.DAYS.convert(diffInMilliesCurrentTo, TimeUnit.MILLISECONDS);
            if(datesDiffFromCurrentTo<0) throw new FutureDateException("To date after current date");

            StringBuilder fromUrl = new StringBuilder();
            fromUrl.append(environment.getProperty("microservice.listen.api"))
                    .append(from)
                    .append("/country/")
                    .append(country);

            HttpHeaders fromHeaders = new HttpHeaders();
            fromHeaders.set("Connection", "keep-alive");
            HttpEntity<String> fromEntity = new HttpEntity<String>(fromHeaders);
            ResponseEntity<String> fromResponse = restTemplate.exchange(fromUrl.toString(), HttpMethod.GET, fromEntity, String.class);
            ObjectMapper fromObjectMapper = new ObjectMapper();
            TotalRoot fromRoot = fromObjectMapper.readValue(fromResponse.getBody(), TotalRoot.class);

            int fromResult = fromRoot.getDates().getCurrentDate().getCountries().getPoland().getTodayConfirmed();

            StringBuilder toUrl = new StringBuilder();
            toUrl.append(environment.getProperty("microservice.listen.api"))
                    .append(to)
                    .append("/country/")
                    .append(country);

            HttpHeaders toHeaders = new HttpHeaders();
            fromHeaders.set("Connection", "keep-alive");
            HttpEntity<String> toEntity = new HttpEntity<String>(toHeaders);
            ResponseEntity<String> toResponse = restTemplate.exchange(toUrl.toString(), HttpMethod.GET, toEntity, String.class);
            ObjectMapper toObjectMapper = new ObjectMapper();
            TotalRoot toRoot = toObjectMapper.readValue(toResponse.getBody(), TotalRoot.class);

            int toResult = toRoot.getDates().getCurrentDate().getCountries().getPoland().getTodayConfirmed();

            return toResult-fromResult;
        }
    }

    public int getCountryDeaths(String requestCountry, String from, String to) throws JsonProcessingException, FromDateAfterToDateException, ParseException, FutureDateException {

        CountryMap countryMap = new CountryMap();
        String country = countryMap.getCountries().get(requestCountry);

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date currentDate = new Date(System.currentTimeMillis());
        String currentDateFormatted = format.format(currentDate);
        RestTemplate restTemplate = new RestTemplate();

        if (from == null || to == null) {
            StringBuilder url = new StringBuilder();
            url.append(environment.getProperty("microservice.listen.api"))
                    .append(currentDateFormatted)
                    .append("/country/")
                    .append(country);

            HttpHeaders headers = new HttpHeaders();
            headers.set("Connection", "keep-alive");
            HttpEntity<String> entity = new HttpEntity<String>(headers);
            ResponseEntity<String> response = restTemplate.exchange(url.toString(), HttpMethod.GET, entity, String.class);
            ObjectMapper objectMapper = new ObjectMapper();
            TotalRoot root = objectMapper.readValue(response.getBody(), TotalRoot.class);

            int todayTotal = root.getDates().getCurrentDate().getCountries().getPoland().getTodayDeaths();

            return todayTotal;

        } else {

            Date fromDate = new SimpleDateFormat("yyyy-MM-dd").parse(from);
            Date toDate = new SimpleDateFormat("yyyy-MM-dd").parse(to);

            long diffInMilliesFromTo = toDate.getTime() - fromDate.getTime();
            long datesDiffFromTo = TimeUnit.DAYS.convert(diffInMilliesFromTo, TimeUnit.MILLISECONDS);
            if (datesDiffFromTo < 0) throw new FromDateAfterToDateException("From date after to date");

            long diffInMilliesFromCurrent = currentDate.getTime() - fromDate.getTime();
            long datesDiffFromCurrent = TimeUnit.DAYS.convert(diffInMilliesFromCurrent, TimeUnit.MILLISECONDS);
            if(datesDiffFromCurrent<0) throw new FutureDateException("From date after current date");

            long diffInMilliesCurrentTo =  currentDate.getTime() - toDate.getTime();
            long datesDiffFromCurrentTo = TimeUnit.DAYS.convert(diffInMilliesCurrentTo, TimeUnit.MILLISECONDS);
            if(datesDiffFromCurrentTo<0) throw new FutureDateException("To date after current date");

            StringBuilder fromUrl = new StringBuilder();
            fromUrl.append(environment.getProperty("microservice.listen.api"))
                    .append(from)
                    .append("/country/")
                    .append(country);

            HttpHeaders fromHeaders = new HttpHeaders();
            fromHeaders.set("Connection", "keep-alive");
            HttpEntity<String> fromEntity = new HttpEntity<String>(fromHeaders);
            ResponseEntity<String> fromResponse = restTemplate.exchange(fromUrl.toString(), HttpMethod.GET, fromEntity, String.class);
            ObjectMapper fromObjectMapper = new ObjectMapper();
            TotalRoot fromRoot = fromObjectMapper.readValue(fromResponse.getBody(), TotalRoot.class);

            int fromResult = fromRoot.getDates().getCurrentDate().getCountries().getPoland().getTodayDeaths();

            StringBuilder toUrl = new StringBuilder();
            toUrl.append(environment.getProperty("microservice.listen.api"))
                    .append(to)
                    .append("/country/")
                    .append(country);

            HttpHeaders toHeaders = new HttpHeaders();
            fromHeaders.set("Connection", "keep-alive");
            HttpEntity<String> toEntity = new HttpEntity<String>(toHeaders);
            ResponseEntity<String> toResponse = restTemplate.exchange(toUrl.toString(), HttpMethod.GET, toEntity, String.class);
            ObjectMapper toObjectMapper = new ObjectMapper();
            TotalRoot toRoot = toObjectMapper.readValue(toResponse.getBody(), TotalRoot.class);

            int toResult = toRoot.getDates().getCurrentDate().getCountries().getPoland().getTodayDeaths();

            return toResult - fromResult;
        }
    }

    public int getCountryRecovered(String requestCountry, String from, String to) throws JsonProcessingException, FutureDateException, FromDateAfterToDateException, ParseException {

        CountryMap countryMap = new CountryMap();
        String country = countryMap.getCountries().get(requestCountry);

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date currentDate = new Date(System.currentTimeMillis());
        String currentDateFormatted = format.format(currentDate);
        RestTemplate restTemplate = new RestTemplate();

        if (from == null || to == null) {
            StringBuilder url = new StringBuilder();
            url.append(environment.getProperty("microservice.listen.api"))
                    .append(currentDateFormatted)
                    .append("/country/")
                    .append(country);

            HttpHeaders headers = new HttpHeaders();
            headers.set("Connection", "keep-alive");
            HttpEntity<String> entity = new HttpEntity<String>(headers);
            ResponseEntity<String> response = restTemplate.exchange(url.toString(), HttpMethod.GET, entity, String.class);
            ObjectMapper objectMapper = new ObjectMapper();
            TotalRoot root = objectMapper.readValue(response.getBody(), TotalRoot.class);

            int todayTotal = root.getDates().getCurrentDate().getCountries().getPoland().getTodayRecovered();

            return todayTotal;

        } else {

            Date fromDate = new SimpleDateFormat("yyyy-MM-dd").parse(from);
            Date toDate = new SimpleDateFormat("yyyy-MM-dd").parse(to);

            long diffInMilliesFromTo = toDate.getTime() - fromDate.getTime();
            long datesDiffFromTo = TimeUnit.DAYS.convert(diffInMilliesFromTo, TimeUnit.MILLISECONDS);
            if (datesDiffFromTo < 0) throw new FromDateAfterToDateException("From date after to date");

            long diffInMilliesFromCurrent = currentDate.getTime() - fromDate.getTime();
            long datesDiffFromCurrent = TimeUnit.DAYS.convert(diffInMilliesFromCurrent, TimeUnit.MILLISECONDS);
            if (datesDiffFromCurrent < 0) throw new FutureDateException("From date after current date");

            long diffInMilliesCurrentTo = currentDate.getTime() - toDate.getTime();
            long datesDiffFromCurrentTo = TimeUnit.DAYS.convert(diffInMilliesCurrentTo, TimeUnit.MILLISECONDS);
            if (datesDiffFromCurrentTo < 0) throw new FutureDateException("To date after current date");

            StringBuilder fromUrl = new StringBuilder();
            fromUrl.append(environment.getProperty("microservice.listen.api"))
                    .append(from)
                    .append("/country/")
                    .append(country);

            HttpHeaders fromHeaders = new HttpHeaders();
            fromHeaders.set("Connection", "keep-alive");
            HttpEntity<String> fromEntity = new HttpEntity<String>(fromHeaders);
            ResponseEntity<String> fromResponse = restTemplate.exchange(fromUrl.toString(), HttpMethod.GET, fromEntity, String.class);
            ObjectMapper fromObjectMapper = new ObjectMapper();
            TotalRoot fromRoot = fromObjectMapper.readValue(fromResponse.getBody(), TotalRoot.class);

            int fromResult = fromRoot.getDates().getCurrentDate().getCountries().getPoland().getTodayRecovered();

            StringBuilder toUrl = new StringBuilder();
            toUrl.append(environment.getProperty("microservice.listen.api"))
                    .append(to)
                    .append("/country/")
                    .append(country);

            HttpHeaders toHeaders = new HttpHeaders();
            fromHeaders.set("Connection", "keep-alive");
            HttpEntity<String> toEntity = new HttpEntity<String>(toHeaders);
            ResponseEntity<String> toResponse = restTemplate.exchange(toUrl.toString(), HttpMethod.GET, toEntity, String.class);
            ObjectMapper toObjectMapper = new ObjectMapper();
            TotalRoot toRoot = toObjectMapper.readValue(toResponse.getBody(), TotalRoot.class);

            int toResult = toRoot.getDates().getCurrentDate().getCountries().getPoland().getTodayRecovered();

            return toResult - fromResult;
        }
    }

    public DailyStatisticsModel getDailyStatistics(String requestCountry, String date) throws NoFoundException, JsonProcessingException {

        CountryMap countryMap = new CountryMap();
        String country = countryMap.getCountries().get(requestCountry);

        RestTemplate restTemplate = new RestTemplate();
        String url = environment.getProperty("microservice.listen.api")+date+"/country/"+country;
        HttpHeaders headers = new HttpHeaders();
        headers.set("Connection","keep-alive");
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
        if(response.getStatusCode().is4xxClientError()){
            throw new NoFoundException("Cannot find response");
        }
        else {
            ObjectMapper objectMapper = new ObjectMapper();
            TotalRoot root = objectMapper.readValue(response.getBody(), TotalRoot.class);
            return new DailyStatisticsModel(
                    date,
                    root.getDates().getCurrentDate().getCountries().getPoland().getTodayNewConfirmed(),
                    root.getDates().getCurrentDate().getCountries().getPoland().getTodayNewDeaths(),
                    root.getDates().getCurrentDate().getCountries().getPoland().getTodayNewRecovered()
            );
        }
    }
}
