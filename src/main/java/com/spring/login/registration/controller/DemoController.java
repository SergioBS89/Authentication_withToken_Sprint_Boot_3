package com.spring.login.registration.controller;

import com.spring.login.registration.model.UserEntity;
import com.spring.login.registration.service.UserEntityService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/demo")
public class DemoController {

    @Autowired
    private UserEntityService userEntityService;

    /**
     * Once we have gotten the token, we can test it in Postman, tab authentication (Bearer token)
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
