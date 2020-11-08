package com.covid19.api.listener.controllers;

import com.covid19.api.listener.model.CovidCountryResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/country")
public class CountryController {

    @GetMapping("/")
    public String getStatus(){
        return "status ok";
    }

    @GetMapping("/all")
    public String allCountries(){
        return "all-countries";
    }

    @GetMapping(
            value = "/{country}",
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XHTML_XML_VALUE}
            )
    public ResponseEntity<CovidCountryResponse> status(@PathVariable("country") String country){

        CovidCountryResponse covid=new CovidCountryResponse();
        covid.setCountry(country);
        covid.setActive(1000);

        return ResponseEntity.status(HttpStatus.OK).body(covid);
    }
}
