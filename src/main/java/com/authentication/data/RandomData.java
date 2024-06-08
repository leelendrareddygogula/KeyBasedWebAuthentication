package com.authentication.data;

import java.util.Random;

import org.springframework.stereotype.Service;

import com.authentication.remote.RandomRemote;

@Service
public class RandomData implements RandomRemote
{

	@Override
	public String generateRandomString(int n) 
	{
		String s = "";
		String allCharacters = "QWERTYUIOPASDFGHJKLZXCVBNM"
				+ "qwertyuiopasdfghjklzxcvbnm"
				+ "1234567890";
		for(int i = 0 ; i < n ; i++)
		{
			Random random = new Random();
			int c = random.nextInt(allCharacters.length());
			s += allCharacters.charAt(c);
		}
		return s;
	}

	@Override
	public int generateRandomNumber() {
		Random random = new Random();
		return random.nextInt(30);
	}
	
	@Override
	public String sliceString(String s, int n) 
	{
		String output = "";
		System.out.println(s);
		for(int i = 0 ; i < n ; i++)
		{
			System.out.print(s.charAt(i));
			output += s.charAt(i);
		}
		System.out.println();
		return output;
	}
	
	
	
}
