package com.covid.search.data;

import com.covid19.api.listener.model.CovidCountryResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="covid19api-com-listener")
public interface Covid19ApiComServiceClient {

    //make a call to another microservice
    @GetMapping("/country/{country}")
    CovidCountryResponse getCountryInfo(@PathVariable("country") String countryName);
}
