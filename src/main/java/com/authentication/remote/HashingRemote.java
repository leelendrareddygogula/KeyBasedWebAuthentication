package com.authentication.remote;

import java.security.NoSuchAlgorithmException;

public interface HashingRemote 
{
	public String getSHA2(String input) throws NoSuchAlgorithmException;
}
