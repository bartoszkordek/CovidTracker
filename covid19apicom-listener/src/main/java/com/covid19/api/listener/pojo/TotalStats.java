package com.covid19.api.listener.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TotalStats {

    @JsonProperty("dates")
    private String dates;

    @JsonProperty("metadata")
    private String metadata;

    @JsonProperty("total")
    private String total;

    @JsonProperty("updated_at")
    private String updatedAt;

    public String getDates() {
        return dates;
    }

    public String getMetadata() {
        return metadata;
    }

    public String getTotal(){
        return total;
    }

    public String getUpdatedAt(){
        return updatedAt;
    }
}
