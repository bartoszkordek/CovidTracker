package com.covid.search.data;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="local-covid-data-service")
public interface LocalCovidDataServiceClient {

    //make a call to Local Covid Data microservice
    @GetMapping("/search/test")
    String getTest();

    @GetMapping("/search/{country}")
    String getCountryStatistics(@PathVariable("country") String countryName);

    @GetMapping("/search/today/total/test")
    int getTodayTotalTest();



}
