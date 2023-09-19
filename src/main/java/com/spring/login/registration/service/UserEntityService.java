package com.spring.login.registration.service;

import com.spring.login.registration.model.UserEntity;
import com.spring.login.registration.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserEntityService {

    @Autowired
    private UserRepository userRepository;

    public List<UserEntity> getAllUsers(){
        return userRepository.findAll();
    }
}
