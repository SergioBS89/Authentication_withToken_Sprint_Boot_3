package com.spring.login.registration.controller;

import com.spring.login.registration.model.UserEntity;
import com.spring.login.registration.service.UserEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/proof")
public class DemoController {

    @Autowired
    private UserEntityService userEntityService;

    /**
     * Once we obtain the token, we can test both Get request are below using Postman, tab authentication (Bearer token: paste the token generated)
     */
    @GetMapping
    public ResponseEntity<String> sayHello(){
        return ResponseEntity.ok("Hellooooo");
    }

    @GetMapping("/all")
    public List<UserEntity> getListUsers(){
        return userEntityService.getAllUsers();
    }
}
