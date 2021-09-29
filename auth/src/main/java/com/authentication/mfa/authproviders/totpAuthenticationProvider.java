package com.authentication.mfa.authproviders;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.authentication.mfa.AuthUserDetailsService;
import com.authentication.mfa.Hash;
import com.authentication.mfa.HashRepository;
import com.authentication.mfa.TOTP;
import com.authentication.mfa.authentications.TotpAuthentication;
import com.authentication.mfa.authentications.UserPasswordAuthentication;

@Component
public class totpAuthenticationProvider implements AuthenticationProvider{
    
	@Autowired
	private HashRepository  hashRepository;
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		
		String username = authentication.getName();
		String otp = (String) authentication.getCredentials();
		
		Optional<Hash> hash = hashRepository.findHashByUserName(username);
		
		Hash h;
		String hh;
		String generateTotp;
		
		if(hash.isPresent())
		{
			try {
				
				    h = hash.get();
				    hh = h.getHash();
				    generateTotp = TOTP.getTOTP(hh);    
				    if(otp.equals(generateTotp)) {
						return new TotpAuthentication(username,otp); 
				       }
				    else
				    {
				    	throw new BadCredentialsException("username or Totp incorrect");
				    }

				  }		 	
				catch(Exception e)
				{
					 System.out.println(e);
				}
		}
		throw new BadCredentialsException("username or Totp incorrect");
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return TotpAuthentication.class.equals(authentication);
	}
	

}
