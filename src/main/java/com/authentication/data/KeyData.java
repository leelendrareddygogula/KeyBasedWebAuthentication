package com.authentication.data;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.authentication.entity.Key;
import com.authentication.remote.KeyRemote;
import com.authentication.repo.KeyRepository;

@Service
public class KeyData implements KeyRemote
{
	
	@Autowired
	private KeyRepository keyRepository;

	@Override
	public Key findKeyByID(String keyIdentifier) 
	{
		if(isKeyAvailable(keyIdentifier))
		{
			return keyRepository.findById(keyIdentifier).get();
		}
		return null;
	}

	@Override
	public boolean isKeyAvailable(String keyIdentifier) {
		try
		{
			keyRepository.findById(keyIdentifier).get();
		}
		catch (NoSuchElementException e) {
			return false;
		}
		return true;
	}
	
	@Override
	public String addNewKey(Key key) 
	{
		if(isKeyAvailable(key.getKeyIdentifier()))
		{
			return "Key Already Exists";
		}
		keyRepository.save(key); 
		return "Key addition Successful";
	}

	
}
