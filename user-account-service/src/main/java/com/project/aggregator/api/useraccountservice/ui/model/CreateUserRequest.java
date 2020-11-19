package com.project.aggregator.api.useraccountservice.ui.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder({
        "firstName",
        "lastName",
        "password",
        "email",
})
public class CreateUserRequest {

    @JsonProperty("firstName")
    @NotNull(message = "Name field cannot be blank.")
    @Size(min = 2, message = "Name field has to have at least 2 characters.")
    private String name;

    @JsonProperty("lastName")
    @NotNull(message = "Surname field cannot be blank.")
    @Size(min = 2, message = "Surname field has to have at least 2 characters.")
    private String surname;

    @JsonProperty("email")
    @NotNull(message = "Email field cannot be blank.")
    @Email
    private String email;

    @JsonProperty("password")
    @NotNull(message = "Password field cannot be blank.")
    @Size(min = 8,max = 24,message = "Password should have from 8 to 24 characters.")
    private String password;

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "CreateUserRequest{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
