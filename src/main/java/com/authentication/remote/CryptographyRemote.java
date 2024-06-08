package com.authentication.remote;

public interface CryptographyRemote 
{
	public String encryption(String text, String key, String salt);
	public String decryption(String cipherText, String key, String salt);
	public String removeEqualSign(String input);
}
