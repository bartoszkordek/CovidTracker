package com.project.aggregator.api.useraccountservice.ui.controller;

import com.project.aggregator.api.useraccountservice.service.UserService;
import com.project.aggregator.api.useraccountservice.shared.UserDTO;
import com.project.aggregator.api.useraccountservice.ui.model.CreateUserRequest;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserController {

    private Environment environment;
    private UserService userService;

    @Autowired
    public UserController(Environment environment, UserService userService) {
        this.environment = environment;
        this.userService = userService;
    }

    @GetMapping("/status/check")
    public String status(){
        return "Working "+environment.getProperty("local.server.port");
    }

    @PostMapping
    public String createUser(@Valid @RequestBody CreateUserRequest createUserRequest){

        ModelMapper modelMapper=new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        UserDTO userDTO=modelMapper.map(createUserRequest,UserDTO.class);

        userService.createUser(userDTO);

        return createUserRequest.toString();
    }
}
