package com.spring.login.registration.controller;

import com.spring.login.registration.model.AuthenticationRequest;
import com.spring.login.registration.model.RegisterRequest;
import com.spring.login.registration.model.TokenResponse;
import com.spring.login.registration.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    /*    { TO TEST REGISTER IN DB
        "firstname": "Sergio",
            "lastname": "Berdiell",
            "email": "sergio@gmail.com",
            "password": "1234",
            "location": "Binefar"
    }*/

    @PostMapping("/register")
    public ResponseEntity<TokenResponse> register(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(authenticationService.register(request));
    }

/*    { TO TEST AUTHENTICATION
        "email":"sergio@gmail.com",
            "password":"1234"
    }*/

    @PostMapping("/authentication")
    public ResponseEntity<TokenResponse> authenticate(@RequestBody AuthenticationRequest request) {
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }
}
