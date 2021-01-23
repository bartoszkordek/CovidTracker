package com.covid19.api.listener.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.covid19.api.listener.exception.FromDateAfterToDateException;
import com.covid19.api.listener.exception.FutureDateException;
import com.covid19.api.listener.exception.NoFoundException;
import com.covid19.api.listener.exception.RestException;
import com.covid19.api.listener.model.DailyStatisticsModel;
import com.covid19.api.listener.model.TodayStatisticsModel;
import com.covid19.api.listener.service.TotalStatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
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
    public int getCountryDeaths(@PathVariable("country") final String country,
                               @RequestParam(required = false) final String from,
                               @RequestParam(required = false) final String to) throws JsonProcessingException, ParseException, FromDateAfterToDateException, FutureDateException, RestException {
        try {
            return totalStatisticsService.getCountryDeaths(country, from, to);
        } catch (FromDateAfterToDateException | FutureDateException e){
            throw new RestException(e.getMessage(), HttpStatus.BAD_REQUEST, e);
        }
    }

    @GetMapping("/{country}/recovered")
    public int getCountryRecovered(@PathVariable("country") final String country,
                                @RequestParam(required = false) final String from,
                                @RequestParam(required = false) final String to) throws JsonProcessingException, ParseException, FromDateAfterToDateException, FutureDateException, RestException {
        try {
            return totalStatisticsService.getCountryRecovered(country, from, to);
        } catch (FromDateAfterToDateException | FutureDateException e){
            throw new RestException(e.getMessage(), HttpStatus.BAD_REQUEST, e);
        }
    }

    @GetMapping("{country}")
    public DailyStatisticsModel getDailyStatistics(@PathVariable("country") final String country,
                                                   @RequestParam(required = true) final String date) throws JsonProcessingException, RestException {
        try{
            return totalStatisticsService.getDailyStatistics(country, date);
        } catch (NoFoundException e){
            throw new RestException(e.getMessage(), HttpStatus.BAD_REQUEST, e);
        }
    }
}
