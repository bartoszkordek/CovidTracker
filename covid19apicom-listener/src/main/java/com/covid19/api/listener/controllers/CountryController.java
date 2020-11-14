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
            value = "/{country}",
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XHTML_XML_VALUE}
            )
    public ResponseEntity<CovidCountryResponse> status(@PathVariable("country") String country){

        CovidCountryResponse covid=new CovidCountryResponse();
        covid.setCountry(country);
        covid.setActive(1000);

        return ResponseEntity.status(HttpStatus.OK).body(covid);
    }

    @GetMapping(
            value = "/test",
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XHTML_XML_VALUE}
    )
    public ResponseEntity<CovidCountryResponse> getSampleEmployee(){

        String url=environment.getProperty("microservice.listen.api");

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
