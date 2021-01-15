package com.project.aggregator.covid19trackingnarrativaservice.map;

import java.util.HashMap;
import java.util.Map;

public class CountryMap {

    private Map<String,String> countries = new HashMap<>();

    public CountryMap(){
        countries.put("DEU", "germany");
        countries.put("FRA", "france");
        countries.put("CHN", "china");
        countries.put("POL", "poland");
        countries.put("ESP", "spain");
        countries.put("GBR", "united_kingdom");
        countries.put("USA", "US");
    }

    public Map<String, String> getCountries() {
        return countries;
    }
}
