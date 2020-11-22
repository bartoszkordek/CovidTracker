package com.project.aggregator.covid19trackingnarrativaservice.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Countries{
    @JsonProperty("Poland")
    private Poland poland;

    public Poland getPoland() {
        return poland;
    }
}
