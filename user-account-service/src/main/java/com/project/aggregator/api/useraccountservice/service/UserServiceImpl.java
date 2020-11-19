package com.project.aggregator.api.useraccountservice.service;

import com.project.aggregator.api.useraccountservice.shared.UserDTO;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserServiceImpl implements UserService{

    @Override
    public UserDTO createUser(UserDTO userDetails) {

        userDetails.setEncryptedPassword("test");
        userDetails.setUserId(UUID.randomUUID().toString());

        System.out.println(userDetails.toString());

        return null;
    }
}
