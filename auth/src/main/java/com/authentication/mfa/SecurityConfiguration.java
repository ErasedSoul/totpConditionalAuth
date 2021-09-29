 package com.authentication.mfa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.authentication.mfa.authproviders.TokenAuthProvider;
import com.authentication.mfa.authproviders.UsernamePasswordAuthProvider;
import com.authentication.mfa.authproviders.totpAuthenticationProvider;
import com.authentication.mfa.filters.EndpointsFilter;
import com.authentication.mfa.filters.UserPasswordAuthFilter;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    
	@Autowired
	private UsernamePasswordAuthProvider authprovider;
	
	@Autowired
	private totpAuthenticationProvider totpAuth;
	
	@Autowired
	private UserPasswordAuthFilter authf;
	
	@Autowired
	private TokenAuthProvider tokenAuth;
	
	@Autowired
	private EndpointsFilter secondFilter;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
		 
		auth.authenticationProvider(authprovider)
		.authenticationProvider(totpAuth)
		.authenticationProvider(tokenAuth);
	}
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		
		http.addFilterAt(authf,BasicAuthenticationFilter.class)
		.addFilterAfter(secondFilter, BasicAuthenticationFilter.class);
	}
	
	@Bean
	public PasswordEncoder getPasswordEncoder() {
		
		return NoOpPasswordEncoder.getInstance();
	}
	
    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception
    {
        return super.authenticationManagerBean();	
    }
	
}
