package com.project.aggregator.covid19trackingnarrativaservice.pojo;

import com.fasterxml.jackson.annotation.JsonAlias;

public class Dates {

    @JsonAlias({"2020-11-01", "2020-11-02", "2020-11-03", "2020-11-04", "2020-11-05",
            "2020-11-06", "2020-11-07", "2020-11-08", "2020-11-09", "2020-11-10",
            "2020-11-11", "2020-11-12", "2020-11-13", "2020-11-14", "2020-11-15",
            "2020-11-16", "2020-11-17", "2020-11-18", "2020-11-19", "2020-11-20"})
    private CurrentDate currentDate;

    public CurrentDate getCurrentDate() {
        return currentDate;
    }
}
