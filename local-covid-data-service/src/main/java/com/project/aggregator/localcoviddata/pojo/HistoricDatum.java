package com.project.aggregator.localcoviddata.pojo;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "date",
        "deathCt",
        "reportedCt",
        "activeCaseCt",
        "recoveredCt"
})
public class HistoricDatum {

    @JsonProperty("date")
    private String date;
    @JsonProperty("deathCt")
    private Integer deathCt;
    @JsonProperty("reportedCt")
    private Integer reportedCt;
    @JsonProperty("activeCaseCt")
    private Object activeCaseCt;
    @JsonProperty("recoveredCt")
    private Integer recoveredCt;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("date")
    public String getDate() {
        return date;
    }

    @JsonProperty("date")
    public void setDate(String date) {
        this.date = date;
    }

    @JsonProperty("deathCt")
    public Integer getDeathCt() {
        return deathCt;
    }

    @JsonProperty("deathCt")
    public void setDeathCt(Integer deathCt) {
        this.deathCt = deathCt;
    }

    @JsonProperty("reportedCt")
    public Integer getReportedCt() {
        return reportedCt;
    }

    @JsonProperty("reportedCt")
    public void setReportedCt(Integer reportedCt) {
        this.reportedCt = reportedCt;
    }

    @JsonProperty("activeCaseCt")
    public Object getActiveCaseCt() {
        return activeCaseCt;
    }

    @JsonProperty("activeCaseCt")
    public void setActiveCaseCt(Object activeCaseCt) {
        this.activeCaseCt = activeCaseCt;
    }

    @JsonProperty("recoveredCt")
    public Integer getRecoveredCt() {
        return recoveredCt;
    }

    @JsonProperty("recoveredCt")
    public void setRecoveredCt(Integer recoveredCt) {
        this.recoveredCt = recoveredCt;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
