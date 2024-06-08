package com.authentication.remote;

import com.authentication.entity.Key;

public interface KeyRemote 
{
	public Key findKeyByID(String keyIdentifier);
	public boolean isKeyAvailable(String keyIdentifier);
	public String addNewKey(Key key);
}
