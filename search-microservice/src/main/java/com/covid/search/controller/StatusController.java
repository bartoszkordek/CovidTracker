package com.covid.search.controller;

import com.covid.search.model.RecoveredResponse;
import com.covid.search.service.CountryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/poland")
public class StatusController {

    private final CountryServiceImpl countryService;

    @Autowired
    public StatusController(CountryServiceImpl countryService) {
        this.countryService = countryService;
    }

    @GetMapping("/confirmed")
    public String getConfirmed(){ return "0 confirmed";}

    @GetMapping("/deaths")
    public String getDeaths(){
        return "0 deaths";
    }

    @GetMapping("/recovered")
    public ResponseEntity<RecoveredResponse> getRecovered(){

        String testCountery="Poland";

        RecoveredResponse response=countryService.getDataByCountry(testCountery);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/active")
    public String getActive(){
        return "0 active";
    }

    @GetMapping("/{country}/total")
    public ResponseEntity<Integer> getTotalCountry(@PathVariable("country") String countryName,
                                                   @RequestParam(required = false) final String from,
                                                   @RequestParam(required = false) final String to){

        int response = countryService.getCountryTotal(countryName, from, to);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/{country}/deaths")
    public ResponseEntity<Integer> getDeathsCountry(@PathVariable("country") String countryName,
                                                   @RequestParam(required = false) final String from,
                                                   @RequestParam(required = false) final String to){

        int response = countryService.getCountryDeaths(countryName, from, to);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
