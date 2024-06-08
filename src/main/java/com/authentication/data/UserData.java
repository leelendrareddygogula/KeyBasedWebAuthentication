package com.authentication.data;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.authentication.entity.User;
import com.authentication.remote.UserRemote;
import com.authentication.repo.UserRepository;

@Service
public class UserData implements UserRemote
{

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public User getUserByUserName(String username) {
		if(userExists(username))
		{
			return userRepository.findById(username).get();
		}
		return null;
	}

	@Override
	public boolean userExists(String username) 
	{
		try
		{
			userRepository.findById(username).get();
		}
		catch (NoSuchElementException e) {
			return false;
		}
		return true;
	}

	@Override
	public String registerUser(User user) {
		if(userExists(user.getUsername()))
		{
			return "User Already Exists";
		}
		userRepository.save(user);
		return "User Saved";
	}	
	
}
