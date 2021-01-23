package com.covid19.api.listener.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Poland{
    private String date;
    private String id;
    private List<Link> links;
    private String name;

    @JsonProperty("name_es")
    private String nameEs;

    @JsonProperty("name_it")
    private String nameIt;
    private List<Object> regions;
    private String source;

    @JsonProperty("today_confirmed")
    private int todayConfirmed;

    @JsonProperty("today_deaths")
    private int todayDeaths;

    @JsonProperty("today_new_confirmed")
    private int todayNewConfirmed;

    @JsonProperty("today_new_deaths")
    private int todayNewDeaths;

    @JsonProperty("today_new_open_cases")
    private int todayNewOpenCases;

    @JsonProperty("today_new_recovered")
    private int todayNewRecovered;

    @JsonProperty("today_open_cases")
    private int todayOpenCases;

    @JsonProperty("today_recovered")
    private int todayRecovered;

    @JsonProperty("today_vs_yesterday_confirmed")
    private double todayVsYesterdayConfirmed;

    @JsonProperty("today_vs_yesterday_deaths")
    private double todayVsYesterdayDeaths;

    @JsonProperty("today_vs_yesterday_open_cases")
    private double todayVsYesterdayOpenCases;

    @JsonProperty("today_vs_yesterday_recovered")
    private double todayVsYesterdayRecovered;

    @JsonProperty("yesterday_confirmed")
    private int yesterdayConfirmed;

    @JsonProperty("yesterday_deaths")
    private int yesterdayDeaths;

    @JsonProperty("yesterday_open_cases")
    private int yesterdayOpenCases;

    @JsonProperty("yesterday_recovered")
    private int yesterdayRecovered;

    public String getDate() {
        return date;
    }

    public String getId() {
        return id;
    }

    public List<Link> getLinks() {
        return links;
    }

    public String getName() {
        return name;
    }

    public String getNameEs() {
        return nameEs;
    }

    public String getNameIt() {
        return nameIt;
    }

    public List<Object> getRegions() {
        return regions;
    }

    public String getSource() {
        return source;
    }

    public int getTodayConfirmed() {
        return todayConfirmed;
    }

    public int getTodayDeaths() {
        return todayDeaths;
    }

    public int getTodayNewConfirmed() {
        return todayNewConfirmed;
    }

    public int getTodayNewDeaths() {
        return todayNewDeaths;
    }

    public int getTodayNewOpenCases() {
        return todayNewOpenCases;
    }

    public int getTodayNewRecovered() {
        return todayNewRecovered;
    }

    public int getTodayOpenCases() {
        return todayOpenCases;
    }

    public int getTodayRecovered() {
        return todayRecovered;
    }

    public double getTodayVsYesterdayConfirmed() {
        return todayVsYesterdayConfirmed;
    }

    public double getTodayVsYesterdayDeaths() {
        return todayVsYesterdayDeaths;
    }

    public double getTodayVsYesterdayOpenCases() {
        return todayVsYesterdayOpenCases;
    }

    public double getTodayVsYesterdayRecovered() {
        return todayVsYesterdayRecovered;
    }

    public int getYesterdayConfirmed() {
        return yesterdayConfirmed;
    }

    public int getYesterdayDeaths() {
        return yesterdayDeaths;
    }

    public int getYesterdayOpenCases() {
        return yesterdayOpenCases;
    }

    public int getYesterdayRecovered() {
        return yesterdayRecovered;
    }
}