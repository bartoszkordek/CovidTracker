package com.project.aggregator.api.useraccountservice.service;

import com.project.aggregator.api.useraccountservice.shared.UserDTO;

public interface UserService {
    UserDTO createUser(UserDTO userDetails);
}
