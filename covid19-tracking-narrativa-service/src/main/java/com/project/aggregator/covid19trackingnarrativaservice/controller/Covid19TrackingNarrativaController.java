package com.project.aggregator.covid19trackingnarrativaservice.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.project.aggregator.covid19trackingnarrativaservice.exception.FromDateAfterToDateException;
import com.project.aggregator.covid19trackingnarrativaservice.exception.FutureDateException;
import com.project.aggregator.covid19trackingnarrativaservice.exception.NoFoundException;
import com.project.aggregator.covid19trackingnarrativaservice.exception.RestException;
import com.project.aggregator.covid19trackingnarrativaservice.model.TodayStatisticsModel;
import com.project.aggregator.covid19trackingnarrativaservice.service.TotalStatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

@RestController
@RequestMapping("/search")
public class Covid19TrackingNarrativaController {

    private TotalStatisticsService totalStatisticsService;

    public Covid19TrackingNarrativaController(TotalStatisticsService totalStatisticsService) {
        this.totalStatisticsService = totalStatisticsService;
    }

    @Autowired
    private Environment environment;


    @GetMapping("/status/check")
    public String status(){
        return "Working "+environment.getProperty("local.server.port");
    }


    @GetMapping("/total/today")
    public TodayStatisticsModel getTodayTotal() throws JsonProcessingException, RestException {
        try{
            return totalStatisticsService.getTodayTotal();
        } catch (NoFoundException e){
            throw new RestException(e.getMessage(), HttpStatus.NOT_FOUND, e);
        }
    }


    @GetMapping("/today")
    public TodayStatisticsModel getTodayTotalCountry(@RequestParam("country") final String country) throws JsonProcessingException, RestException {
        try{
            return totalStatisticsService.getTodayTotalCountry(country);
        } catch (NoFoundException e){
            throw new RestException(e.getMessage(), HttpStatus.NOT_FOUND, e);
        }
    }

    @GetMapping("/stats")
    public TodayStatisticsModel getCountryDate(@RequestParam("country") final String country,
                                               @RequestParam("date") final String date) throws JsonProcessingException, RestException {
        try{
            return totalStatisticsService.getCountryDate(country, date);
        } catch (FutureDateException | ParseException e){
            throw new RestException(e.getMessage(), HttpStatus.BAD_REQUEST, e);
        }
    }

    @GetMapping("/{country}/total")
    public int getCountryTotal(@PathVariable("country") final String country,
                               @RequestParam(required = false) final String from,
                               @RequestParam(required = false) final String to) throws JsonProcessingException, ParseException, FromDateAfterToDateException, FutureDateException, RestException {
        try {
            return totalStatisticsService.getCountryTotal(country, from, to);
        } catch (FromDateAfterToDateException | FutureDateException e){
            throw new RestException(e.getMessage(), HttpStatus.BAD_REQUEST, e);
        }
    }

    @GetMapping("/{country}/deaths")
    public int getCountryDeaths(@PathVariable("country") String country,
                               @RequestParam(required = false) final String from,
                               @RequestParam(required = false) final String to) throws JsonProcessingException, ParseException, FromDateAfterToDateException, FutureDateException, RestException {
        try {
            return totalStatisticsService.getCountryDeaths(country, from, to);
        } catch (FromDateAfterToDateException | FutureDateException e){
            throw new RestException(e.getMessage(), HttpStatus.BAD_REQUEST, e);
        }
    }

}
