package com.authentication.data;

import java.nio.charset.StandardCharsets;
import java.security.spec.KeySpec;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.stereotype.Service;

import com.authentication.remote.CryptographyRemote;

@Service
public class CryptographyData implements CryptographyRemote
{

	@Override
	public String encryption(String text, String key, String salt) 
	{
			try 
			{ 
	            // Create default byte array 
	            byte[] iv = { 0, 0, 0, 0, 0, 0, 0, 0, 
	                          0, 0, 0, 0, 0, 0, 0, 0 }; 
	            IvParameterSpec ivspec 
	                = new IvParameterSpec(iv); 
	  
	            // Create SecretKeyFactory object 
	            SecretKeyFactory factory 
	                = SecretKeyFactory.getInstance( 
	                    "PBKDF2WithHmacSHA256"); 
	            
	            // Create KeySpec object and assign with 
	            // constructor 
	            KeySpec spec = new PBEKeySpec( 
	            		key.toCharArray(), salt.getBytes(), 
	                65536, 256); 
	            SecretKey tmp = factory.generateSecret(spec); 
	            SecretKeySpec secretKey = new SecretKeySpec( 
	                tmp.getEncoded(), "AES"); 
	  
	            Cipher cipher = Cipher.getInstance( 
	                "AES/CBC/PKCS5Padding"); 
	            cipher.init(Cipher.ENCRYPT_MODE, secretKey, 
	                        ivspec); 
	            // Return encrypted string 
	            return Base64.getEncoder().encodeToString( 
	                cipher.doFinal(text.getBytes( 
	                    StandardCharsets.UTF_8))); 
	        } 
	        catch (Exception e) 
			{ 
	            System.out.println("Error while encrypting: "
	                               + e.toString()); 
	        } 
	        return null;
	}


	@Override
	public String decryption(String cipherText, String key, String salt) 
	{
		try 
		{ 
            // Default byte array 
            byte[] iv = { 0, 0, 0, 0, 0, 0, 0, 0, 
                          0, 0, 0, 0, 0, 0, 0, 0 }; 
            // Create IvParameterSpec object and assign with 
            // constructor 
            IvParameterSpec ivspec 
                = new IvParameterSpec(iv); 
  
            // Create SecretKeyFactory Object 
            SecretKeyFactory factory 
                = SecretKeyFactory.getInstance( 
                    "PBKDF2WithHmacSHA256"); 
  
            // Create KeySpec object and assign with 
            // constructor 
            KeySpec spec = new PBEKeySpec( 
            		key.toCharArray(), salt.getBytes(), 
                65536, 256); 
            SecretKey tmp = factory.generateSecret(spec); 
            SecretKeySpec secretKey = new SecretKeySpec( 
                tmp.getEncoded(), "AES"); 
  
            Cipher cipher = Cipher.getInstance( 
                "AES/CBC/PKCS5PADDING"); 
            cipher.init(Cipher.DECRYPT_MODE, secretKey, 
                        ivspec); 
            // Return decrypted string 
            return new String(cipher.doFinal( 
                Base64.getDecoder().decode(cipherText))); 
        } 
        catch (Exception e) 
		{ 
            System.out.println("Error while decrypting: " + e.toString()); 
            return "Corrupted";
        } 
	}


	@Override
	public String removeEqualSign(String input) 
	{
		String s = "";
		for(int i = 0 ; i < input.length() ; i++)
		{
			if(input.charAt(i) != '=')
			{
				s += input.charAt(i);
			}
			else
			{
				break;
			}
		}
		return s;
	}
	
}
