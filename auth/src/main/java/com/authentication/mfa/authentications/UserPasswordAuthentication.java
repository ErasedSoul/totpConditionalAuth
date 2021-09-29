package com.authentication.mfa.authentications;

import java.util.Collection;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

public class UserPasswordAuthentication extends UsernamePasswordAuthenticationToken {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public UserPasswordAuthentication(Object principal, Object credentials) {
		super(principal, credentials);
	}
	public UserPasswordAuthentication(Object principal, Object credentials,Collection<? extends GrantedAuthority> authorities) {
		super(principal, credentials,authorities);
	}
}
