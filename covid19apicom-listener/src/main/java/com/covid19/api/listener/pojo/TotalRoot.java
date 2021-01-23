package com.covid19.api.listener.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TotalRoot {
    private Dates dates;
    private Metadata metadata;
    private Total total;

    @JsonProperty("updated_at")
    private String updatedAt;

    public Dates getDates() {
        return dates;
    }

    public Metadata getMetadata() {
        return metadata;
    }

    public Total getTotal() {
        return total;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }
}
