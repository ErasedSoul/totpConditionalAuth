package com.authentication.mfa;

import java.security.GeneralSecurityException;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthGateWay {
	
	@RequestMapping("/")
	public String cc()
	{
		return "Welcome to the MFA app."
				+ "go to /login to login "
				+"go to /register to register";
	}
	
	@RequestMapping("/register")
	public String foo()
	{
		return "foo";
	}
	
	@PutMapping("/dashboard")
	public String dashboard() throws GeneralSecurityException
	{
		
		
		return null;
		
	}
}
