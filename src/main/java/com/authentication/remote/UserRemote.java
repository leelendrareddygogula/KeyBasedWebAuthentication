package com.authentication.remote;

import com.authentication.entity.User;

public interface UserRemote 
{
	public User getUserByUserName(String username);
	public boolean userExists(String username);
	public String registerUser(User user);
}
