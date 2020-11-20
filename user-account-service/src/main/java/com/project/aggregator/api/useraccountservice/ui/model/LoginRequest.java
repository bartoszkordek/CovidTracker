package com.project.aggregator.api.useraccountservice.ui.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder({
        "email",
        "password"
})
public class LoginRequest {

    @JsonProperty("email")
    @NotNull(message = "Email field cannot be blank.")
    @Email
    private String email;

    @JsonProperty("password")
    @NotNull(message = "Password field cannot be blank.")
    @Size(min = 8,max = 24,message = "Password should have from 8 to 24 characters.")
    private String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
