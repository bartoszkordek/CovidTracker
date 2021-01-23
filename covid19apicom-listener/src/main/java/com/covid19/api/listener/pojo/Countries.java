package com.covid19.api.listener.pojo;

import com.fasterxml.jackson.annotation.JsonAlias;

public class Countries{
    @JsonAlias({"Poland", "Germany", "France", "China", "Spain", "United Kingdom", "US"})
    private Poland poland;

    public Poland getPoland() {
        return poland;
    }
}
