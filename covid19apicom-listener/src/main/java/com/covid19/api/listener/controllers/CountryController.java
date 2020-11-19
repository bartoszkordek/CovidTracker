package com.covid19.api.listener.controllers;

import com.covid19.api.listener.model.CovidCountryResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/country")
public class CountryController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private Environment environment;

    @GetMapping("/")
    public String getStatus(){
        return "status ok";
    }

    @GetMapping("/all")
    public String allCountries(){
        return "all-countries";
    }

    @GetMapping(
            value = "/{country}/today",
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XHTML_XML_VALUE}
    )
    public ResponseEntity<CovidCountryResponse> getCountryData(@PathVariable("country") String country){

        StringBuilder strBuilder=new StringBuilder();
        strBuilder.append(environment.getProperty("microservice.listen.api"));
        strBuilder.append("/dayone/country/");
        strBuilder.append(country.toLowerCase());

        String url=strBuilder.toString();

        ResponseEntity<CovidCountryResponse[]> response=restTemplate
                .getForEntity(url,CovidCountryResponse[].class);

        ObjectMapper mapper=new ObjectMapper();

        CovidCountryResponse[] countries=mapper
                .convertValue(response.getBody(),CovidCountryResponse[].class);

        CovidCountryResponse returnValue=countries[0];

        System.out.println(returnValue);

        return ResponseEntity.status(HttpStatus.OK).body(returnValue);
    }
}