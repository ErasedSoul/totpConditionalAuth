package com.authentication.mfa;

import java.security.SecureRandom;

import org.springframework.stereotype.Service;

import jodd.util.Base32;



@Service
public class SecretGenerator {
	
	private static SecureRandom Random = new SecureRandom();
	private static int len = 10;
	private static final String characters="0123456789abcdefghijklmnopqrstuvwxyz";
	
	private SecretGenerator() {}
	
	private static String base32(String data)
	{
	    return Base32.encode(data.getBytes());	
	}
	
	public static String getNewSecretKey()
	{
		StringBuffer buff = new StringBuffer(len);
		for(int i = 0;i < len;i++)
		{
			int pos = Random.nextInt(characters.length());
			buff.append(characters.charAt(pos));
		}
		return base32(buff.toString());
	}

	public static void main(String[] args) {
		
		System.out.println(getNewSecretKey());
	}

}
