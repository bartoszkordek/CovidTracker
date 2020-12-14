package com.project.aggregator.api.useraccountservice.ui.controller;

import com.project.aggregator.api.useraccountservice.service.UserService;
import com.project.aggregator.api.useraccountservice.shared.UserDTO;
import com.project.aggregator.api.useraccountservice.ui.model.CreateUserRequest;
import com.project.aggregator.api.useraccountservice.ui.model.CreateUserResponse;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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

    @GetMapping("/status/check/git")
    public String statusGit(){
        return "Say hi from github: "+environment.getProperty("git.hello");
    }

    @PostMapping(
            consumes = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE}
    )
    public ResponseEntity<CreateUserResponse> createUser(@Valid @RequestBody CreateUserRequest createUserRequest){

        ModelMapper modelMapper=new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        UserDTO userDTORequest=modelMapper.map(createUserRequest,UserDTO.class);

        UserDTO userDTOResponse=userService.createUser(userDTORequest);

        CreateUserResponse createUserResponse=modelMapper
                .map(userDTOResponse,CreateUserResponse.class);

        return ResponseEntity.status(HttpStatus.CREATED).body(createUserResponse);
    }
}
