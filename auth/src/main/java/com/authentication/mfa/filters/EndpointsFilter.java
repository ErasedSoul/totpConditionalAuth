package com.authentication.mfa.filters;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.authentication.mfa.authentications.TokenAuthentication;
import com.authentication.mfa.authproviders.AuthTokens;

@Component
public class EndpointsFilter extends OncePerRequestFilter{
	
	private AuthenticationManager authenticationManager;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		String token = request.getHeader("Authorization");
		
		
		TokenAuthentication authentication = new TokenAuthentication(token,token);
		Authentication auth = null;
		try {
		auth = authenticationManager.authenticate(authentication);
		}
		catch(AuthenticationException e) {
			response.setStatus(403, "Forbidden");
		}
		
		
		SecurityContextHolder.getContext().setAuthentication(auth);
		
		filterChain.doFilter(request, response);	
		
		
	}
    
	
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
    	
		return !request.getServletPath().equals("/login");
	  
    }
	  
}
