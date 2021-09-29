package com.authentication.mfa.authproviders;

import java.util.UUID;

import org.springframework.stereotype.Component;

@Component
public class GenerateToken {
     
	 public static String newToken() {
		 
		 return UUID.randomUUID().toString();
	 }
}
