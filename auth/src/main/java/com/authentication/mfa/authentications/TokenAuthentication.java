package com.authentication.mfa.authentications;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class TokenAuthentication extends UsernamePasswordAuthenticationToken{

	public TokenAuthentication(Object principal, Object credentials) {
		super(principal, credentials);
	}

	
    
	   
}
