package com.project.aggregator.localcoviddata.map;

import java.util.HashMap;
import java.util.Map;

public class CountryMap {

    Map<String,String> countries = new HashMap<>();

    public CountryMap(){
        countries.put("DEU", "DE");
        countries.put("FRA", "FR");
        countries.put("CHN", "CN");
        countries.put("POL", "PL");
        countries.put("ESP", "ES");
        countries.put("GBR", "GB");
        countries.put("USA", "US");
    }
}
