package com.authentication.remote;

public interface RandomRemote 
{
	public String generateRandomString(int n);
	public int generateRandomNumber();
	public String sliceString(String s, int n);
}
