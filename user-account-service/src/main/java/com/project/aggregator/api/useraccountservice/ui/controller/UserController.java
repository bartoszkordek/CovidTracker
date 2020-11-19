package com.project.aggregator.api.useraccountservice.ui.controller;

import com.project.aggregator.api.useraccountservice.ui.model.CreateUserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private Environment environment;

    @GetMapping("/status/check")
    public String status(){
        return "Working "+environment.getProperty("local.server.port");
    }

    @PostMapping
    public String createUser(@Valid @RequestBody CreateUserRequest createUserRequest){

        return createUserRequest.toString();
    }
}
