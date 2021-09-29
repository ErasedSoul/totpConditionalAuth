package com.authentication.mfa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackageClasses = UserRepository.class)
public class Authentication {

	public static void main(String[] args) {
		   SpringApplication.run(Authentication.class, args);
		
	}

}
