package com.project.aggregator.api.useraccountservice.ui.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({
        "id",
        "firstName",
        "lastName",
        "email"
})
public class CreateUserResponse {

    @JsonProperty("firstName")
    private String name;

    @JsonProperty("lastName")
    private String surname;

    @JsonProperty("email")
    private String email;

    @JsonProperty("id")
    private String userId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "CreateUserResponse{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                ", userId='" + userId + '\'' +
                '}';
    }
}
