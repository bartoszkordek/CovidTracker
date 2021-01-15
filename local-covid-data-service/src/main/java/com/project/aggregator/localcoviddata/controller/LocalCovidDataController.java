package com.project.aggregator.localcoviddata.controller;

import com.project.aggregator.localcoviddata.exception.FromDateAfterToDateException;
import com.project.aggregator.localcoviddata.exception.FutureDateException;
import com.project.aggregator.localcoviddata.exception.RestException;
import com.project.aggregator.localcoviddata.model.CountryStatisticsModel;
import com.project.aggregator.localcoviddata.model.StatisticsModel;
import com.project.aggregator.localcoviddata.service.LocalStatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;


@RestController
@RequestMapping("/search")
public class LocalCovidDataController {

    private final LocalStatisticsService localStatisticsService;

    public LocalCovidDataController(LocalStatisticsService localStatisticsService){
        this.localStatisticsService=localStatisticsService;
    }

    @Autowired
    private Environment environment;


    @GetMapping("/status/check")
    public String status(){
        return "Working "+environment.getProperty("local.server.port");
    }


    @GetMapping("/yesterday")
    public StatisticsModel getYesterdayStatistics(@RequestParam("country") final String country) throws IOException, NoSuchAlgorithmException, KeyManagementException {
        return localStatisticsService.getYesterdayStatistics(country);
    }

    @GetMapping("/statistics")
    public StatisticsModel getCountryDate(@RequestParam("country") final String country,
                                          @RequestParam("date") final String date) throws RestException {
        try{
            return localStatisticsService.getCountryDate(country, date);
        } catch (FutureDateException | IOException | KeyManagementException | NoSuchAlgorithmException | ParseException e){
            throw new RestException(e.getMessage(), HttpStatus.BAD_REQUEST, e);
        }

    }

    @GetMapping("/{country}")
    public CountryStatisticsModel getCountryResults(@PathVariable("country") final String country) throws RestException {
        try{
            return localStatisticsService.getCountryResults(country);
        } catch (NoSuchAlgorithmException | KeyManagementException | IOException e){
            throw new RestException(e.getMessage(), HttpStatus.BAD_REQUEST, e);
        }
    }

    @GetMapping("today/total/test")
    public int getCountryTodayTotalTest() {
        return  1376389;
    }
}
