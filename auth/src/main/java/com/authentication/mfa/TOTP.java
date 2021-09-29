package com.authentication.mfa;

import java.security.GeneralSecurityException;
import java.time.Instant;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import org.springframework.stereotype.Service;
import jodd.util.Base32;

@Service
public class TOTP {
	
	
	private static long Tx = 30;
	
	
	private static byte[] getHash(byte[] key,byte[] data) throws GeneralSecurityException
	{
		Mac mac = Mac.getInstance("HmacSHA1");
		mac.init(new SecretKeySpec(key,"RAW"));
		return mac.doFinal(data);
	}
	
	private static long getEpochTime()
	{
		return Instant.now().getEpochSecond();
	}
	
	
	private static long getSteps(long epochTime)
	{	
	   return (epochTime/Tx); 	
	}
	
	
	public static String getTOTP(String secret) throws GeneralSecurityException
	{
		long time = getEpochTime();
		long steps = getSteps(time);
		
		byte[] data = new byte[8];
		
		for(int i = 0;i < 8;i++)
		{
		   	data[7-i] = (byte) (steps & 255);
		   	System.out.print(data[7-i]+" ");
		   	steps = steps >> 8;
		}
		System.out.println();
		
		byte [] key = Base32.decode(secret);
		
		byte[] hash = getHash(key,data);
		
		int offset = hash[hash.length - 1] & 15;

        int binary =
            ((hash[offset] & 127) << 24) | 
            ((hash[offset + 1] & 255) << 16) |
            ((hash[offset + 2] & 255) << 8) |
            (hash[offset + 3] & 255);
        
        int otp = binary % 1000000;

       String result = Integer.toString(otp);
        while (result.length() < 6) {
            result = "0" + result;
        }
        return result;		
		
	}
	
	
	// For test
	public static void main(String args[])
	{
		String sharedSecret = "OVUXG5LXMUZDQOJTG43GKMRT";
		
		String str = "uweiohr7fr";
	    
	    String encoded = Base32.encode(str.getBytes());
	    System.out.println(encoded);
	
		try {
		   String totp = getTOTP(sharedSecret);
		   System.out.println(totp);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

	}
	
}
