package com.authentication.mfa.authproviders;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Component;

@Component
public class AuthTokens {
   
	 private static Set<String> tokens = new HashSet<String>();
	 
	 public static void add(String token)
	 {
		  tokens.add(token);
	 }
	 
	 public static boolean contains(String token)
	 {
		  return token.contains(token);
	 }
	 
}
