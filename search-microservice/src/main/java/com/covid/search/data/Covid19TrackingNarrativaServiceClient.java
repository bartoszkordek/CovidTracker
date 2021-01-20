package com.covid.search.data;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name="covid19-tracking-narrativa-service")
public interface Covid19TrackingNarrativaServiceClient {

    //make a call to Covid19TrackingNarrativa microservice
    @GetMapping("/search/{country}/total")
    int getTotalCountry(@PathVariable("country") final String countryName,
                        @RequestParam(required = false) final String from,
                        @RequestParam(required = false) final String to);

    @GetMapping("/search/{country}/deaths")
    int getDeathsCountry(@PathVariable("country") final String countryName,
                        @RequestParam(required = false) final String from,
                        @RequestParam(required = false) final String to);

    @GetMapping("/search/{country}/recovered")
    int getRecoveredCountry(@PathVariable("country") final String countryName,
                            @RequestParam(required = false) final String from,
                            @RequestParam(required = false) final String to);

    @GetMapping("/search/{country}")
    String getDailyStatistics(@PathVariable("country") final String countryName,
                              @RequestParam final String date);
}
