package com.authentication.mfa.filters;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.authentication.mfa.Authentication;
import com.authentication.mfa.HashRepository;
import com.authentication.mfa.TOTP;
import com.authentication.mfa.authentications.TotpAuthentication;
import com.authentication.mfa.authentications.UserPasswordAuthentication;
import com.authentication.mfa.authproviders.AuthTokens;
import com.authentication.mfa.authproviders.GenerateToken;

@Component
public class UserPasswordAuthFilter extends OncePerRequestFilter{
    
	
	@Autowired 
	AuthenticationManager authenticationManager;
	
	@Autowired
	HashRepository hashRepository;
	
	@Autowired
	AuthTokens stateTokens;
	
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// step 1: User name and password
		
		//Step 2:TOTP
		String username = request.getHeader("username");
		String password = request.getHeader("password");
		String totp = request.getHeader("totp");
		
		if(totp != null)
		{
			// use totp 
			TotpAuthentication a = new TotpAuthentication(username,totp);
			try {
			org.springframework.security.core.Authentication b = authenticationManager.authenticate(a);
			}
			catch(AuthenticationException E)
			{
				response.setStatus(403, "Forbidden");
			}
			String newToken = GenerateToken.newToken();
			
			stateTokens.add(newToken);
			response.setHeader("Authorization",newToken);
		}
		else
		{
		   // use username and password
			UserPasswordAuthentication a = new UserPasswordAuthentication(username,password);
			try {
				org.springframework.security.core.Authentication b = authenticationManager.authenticate(a);
				}
				catch(AuthenticationException E)
				{
					response.setStatus(403, "Forbidden");
				}
			
			String newToken = GenerateToken.newToken();
			
			stateTokens.add(newToken);
			response.setHeader("Authorization",newToken);
		}
		
	}
//	protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
//		
//		return !request.getServletPath().equals("/login");
//	}
           
}
