package com.microservices.oauth.springclientcredentialauthserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@EnableResourceServer
@SpringBootApplication
public class SpringClientCredentialAuthServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringClientCredentialAuthServerApplication.class, args);
	}
	//This method will be used to check if the user has a valid token to access the resource
	@RequestMapping("/validateUser")
	public Principal user(Principal user) {
		return user;
	}

}
