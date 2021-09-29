package com.authentication.mfa.authproviders;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import com.authentication.mfa.authentications.TokenAuthentication;


@Component
public class TokenAuthProvider implements AuthenticationProvider {
    
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		// TODO Auto-generated method stub
		
		String token = (String) authentication.getName();
		boolean exist = AuthTokens.contains(token);
		
		if(exist)
		{
			return new TokenAuthentication(token,token);
		}
		throw new BadCredentialsException("Invalid Tokens");
		
	}

	@Override
	public boolean supports(Class<?> authentication) {
		// TODO Auto-generated method stub
		return TokenAuthentication.class.equals(authentication);
	}

}
