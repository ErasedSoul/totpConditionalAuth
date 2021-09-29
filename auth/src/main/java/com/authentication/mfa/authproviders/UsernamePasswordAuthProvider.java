package com.authentication.mfa.authproviders;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.authentication.mfa.AuthUserDetailsService;
import com.authentication.mfa.authentications.UserPasswordAuthentication;

@Component
public class UsernamePasswordAuthProvider implements AuthenticationProvider{
    
	
	@Autowired
	private AuthUserDetailsService userDetailsService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
	
		String username = authentication.getName();
		String password = (String) authentication.getCredentials();
		
		UserDetails user = userDetailsService.loadUserByUsername(username);
		
		if(passwordEncoder.matches(password, user.getPassword())) {
			return new UserPasswordAuthentication(username,password,user.getAuthorities());
		}
		throw new BadCredentialsException("username or password incorrect");
	}

	@Override
	public boolean supports(Class<?> authentication) {
		
		return UserPasswordAuthentication.class.equals(authentication); 
	}

}
