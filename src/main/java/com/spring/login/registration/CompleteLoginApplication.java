package com.spring.login.registration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;

@SpringBootApplication
public class CompleteLoginApplication {

	public static void main(String[] args) {
		SpringApplication.run(CompleteLoginApplication.class, args);
	}

}
