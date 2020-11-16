package com.covid.search.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("status")
public class StatusController {

    @GetMapping("/confirmed")
    public String getConfirmed(){
        return "0 confirmed";
    }

    @GetMapping("/deaths")
    public String getDeaths(){
        return "0 deaths";
    }

    @GetMapping("/recovered")
    public String getRecovered(){
        return "0 recovered";
    }

    @GetMapping("/active")
    public String getActive(){
        return "0 active";
    }
}
