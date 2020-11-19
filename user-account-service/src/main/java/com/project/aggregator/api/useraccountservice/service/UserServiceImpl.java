package com.project.aggregator.api.useraccountservice.service;

import com.project.aggregator.api.useraccountservice.data.entity.UserEntity;
import com.project.aggregator.api.useraccountservice.data.repository.UserDAO;
import com.project.aggregator.api.useraccountservice.shared.UserDTO;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserServiceImpl implements UserService{

    private UserDAO userDAO;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserServiceImpl(UserDAO userDAO,BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userDAO = userDAO;
        this.bCryptPasswordEncoder=bCryptPasswordEncoder;
    }

    @Override
    public UserDTO createUser(UserDTO userDetails) {

        userDetails.setEncryptedPassword(
                bCryptPasswordEncoder.encode(userDetails.getPassword())
        );
        userDetails.setUserId(UUID.randomUUID().toString());

        ModelMapper modelMapper=new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        UserEntity userEntity=modelMapper.map(userDetails,UserEntity.class);

        userDAO.save(userEntity);

        UserDTO userResponse=modelMapper.map(userEntity,UserDTO.class);

        return userResponse;
    }
}
