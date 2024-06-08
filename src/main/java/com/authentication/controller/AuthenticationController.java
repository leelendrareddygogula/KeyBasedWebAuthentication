package com.authentication.controller;

import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.authentication.entity.Authentication;
import com.authentication.entity.Key;
import com.authentication.entity.User;
import com.authentication.remote.CryptographyRemote;
import com.authentication.remote.HashingRemote;
import com.authentication.remote.KeyRemote;
import com.authentication.remote.RandomRemote;
import com.authentication.remote.UserRemote;

@RestController
public class AuthenticationController 
{
	@Autowired
	private UserRemote userRemote;
	@Autowired
	private RandomRemote randomRemote;
	@Autowired
	private KeyRemote keyRemote;
	@Autowired
	private HashingRemote hashingRemote;
	@Autowired
	private CryptographyRemote cryptographyRemote;
	
	@GetMapping("/")
	public String home() 
	{
		return "HelloWorld";
	}
	
	@PostMapping("/signup")
	public Map<String, String> newUserRegistration(@RequestBody User user)
	{
		Map<String, String> map = new HashMap<String, String>();
		String authKeyOne = "";  //Username
		String authKeyTwo = ""; //Password
		String authKeyThree = ""; //Role
		
		String userNameHash = null, passwordHash = null, roleHash = null;
		
		try
		{
			userNameHash = hashingRemote.getSHA2(user.getUsername());
			passwordHash = hashingRemote.getSHA2(user.getPassword());
			roleHash = hashingRemote.getSHA2(user.getRole());
		}
		catch (NoSuchAlgorithmException e) 
		{
			
		}
		User user2 = new User();
		user2.setAddress(user.getAddress());
		user2.setMail(user.getMail());
		user2.setMobile(user.getMobile());
		user2.setName(user.getName());
		user2.setName(user.getName());
		user2.setPassword(passwordHash);
		user2.setRole(roleHash);
		user2.setUsername(userNameHash);
		String response = userRemote.registerUser(user2);
		if(response.equals("User Already Exists"))
		{
			map.put("message", "User with the following details exist");
			map.put("key", null);
			return map;
		}
		Key key = new Key();
		String keyIdentifier = null;   //Length 6
		while(true)
		{
			keyIdentifier = randomRemote.generateRandomString(6);
			if(!keyRemote.isKeyAvailable(keyIdentifier))
			{
				break;
			}
		}
		String uniqueKey = randomRemote.generateRandomString(30);
		String salt = randomRemote.generateRandomString(10);
		int additionalCharacters = 0;
		String euName = cryptographyRemote.encryption(user.getUsername(), uniqueKey, salt);
		String encUName = cryptographyRemote.removeEqualSign(euName);
		additionalCharacters = randomRemote.generateRandomNumber();
		String additionalCharactersUname = randomRemote.generateRandomString(additionalCharacters);
		
		authKeyOne += (encUName + additionalCharactersUname + keyIdentifier);
		
		String pwdEnc = cryptographyRemote.encryption(user.getPassword(), uniqueKey, salt);
		String pwdEncnE = cryptographyRemote.removeEqualSign(pwdEnc);
		String addCharactersPwd = randomRemote.generateRandomString((additionalCharacters * 2));
		
		authKeyTwo += (pwdEncnE + addCharactersPwd);
		
		String roleEnc = cryptographyRemote.encryption(user.getRole(), uniqueKey, salt);
		String roleEncE = cryptographyRemote.removeEqualSign(roleEnc);
		String additionalCharactersRole = randomRemote.generateRandomString(additionalCharacters);
		
		authKeyThree += (roleEncE + additionalCharactersRole);		
		/************************************************************************/
		key.setAdditionalCharacters(additionalCharacters);
		key.setKeyIdentifier(keyIdentifier);
		key.setSalt(salt);
		key.setUniqueKey(uniqueKey);
		keyRemote.addNewKey(key);
		/***********************************************************************/
		String finalAuthKey = authKeyOne + ":" + authKeyTwo + ":" + authKeyThree;
		map.put("message", "success");
		map.put("key", finalAuthKey);
		return map;
	}
	
	@PostMapping("/login")
	public Map<String, String> authenticateUser(@RequestBody Authentication authentication)
	{
		String authKey = authentication.getAuthKey();
		String[] values = authKey.split(":");
		
		Map<String, String> map = new LinkedHashMap<String, String>();
		
		if(values.length != 3)
		{
			map.put("message", "corrupted");
			map.put("username", null);
			map.put("role", null);
			return map;
		}
		
		String username = values[0];
		String password = values[1];
		String role = values[2];
		if(!(username.length() > 0) || !(password.length() > 0) || !(role.length() > 0))
		{
			map.put("message", "corrupted");
			map.put("username", null);
			map.put("role", null);
			return map;
		}
		String keyIdentifier = "" +
				username.charAt(username.length() - 6) +
				username.charAt(username.length() - 5) +
				username.charAt(username.length() - 4) +
				username.charAt(username.length() - 3) +
				username.charAt(username.length() - 2) +
				username.charAt(username.length() - 1);
		
		boolean keyAvailable = keyRemote.isKeyAvailable(keyIdentifier);
		if(!keyAvailable)
		{
			map.put("message", "fail");
			map.put("username", null);
			map.put("role", null);
			return map;
		}
		
		Key key = keyRemote.findKeyByID(keyIdentifier);
		String encUserName = randomRemote.sliceString(username, username.length() - key.getAdditionalCharacters() - key.getKeyIdentifier().length());
		String userName = cryptographyRemote.decryption(encUserName, key.getUniqueKey(), key.getSalt());
		
		if(userName.equals("Corrupted"))
		{
			map.put("message", "corrupted");
			map.put("username", null);
			map.put("role", null);
			return map;
		}
		
		String usernameHash = null;
		try
		{
			usernameHash = hashingRemote.getSHA2(userName);
		}
		catch (NoSuchAlgorithmException e) {
			// TODO: handle exception
		}
		boolean userExists = userRemote.userExists(usernameHash);
		if(!userExists)
		{
			map.put("message", "fail");
			map.put("username", null);
			map.put("role", null);
			return map;
		}
		User user = userRemote.getUserByUserName(usernameHash);
		
		String encryptedPassword = randomRemote.sliceString(password, password.length() - (key.getAdditionalCharacters() * 2));
		String decryptedPassword = cryptographyRemote.decryption(encryptedPassword, key.getUniqueKey(), key.getSalt());
		
		if(decryptedPassword.equals("Corrupted"))
		{
			map.put("message", "corrupted");
			map.put("username", null);
			map.put("role", null);
			return map;
		}
		
		String passwordHash = null;
		try
		{
			passwordHash = hashingRemote.getSHA2(decryptedPassword);
		}
		catch (NoSuchAlgorithmException e) {
			
		}
		
		String encryptedRole = randomRemote.sliceString(role, role.length() - key.getAdditionalCharacters());
		String decryptedRole = cryptographyRemote.decryption(encryptedRole, key.getUniqueKey(), key.getSalt());
		
		if(decryptedRole.equals("Corrupted"))
		{
			map.put("message", "corrupted");
			map.put("username", null);
			map.put("role", null);
			return map;
		}
		
		String roleHash = null;
		try
		{
			roleHash = hashingRemote.getSHA2(decryptedRole);
		}
		catch (NoSuchAlgorithmException e) {
			// TODO: handle exception
		}
		if(user.getUsername().equals(usernameHash) && user.getPassword().equals(passwordHash)
				&& user.getRole().equals(roleHash))
		{
			map.put("message", "success");
			map.put("username", usernameHash);
			map.put("role", roleHash);
			return map;
		}
		else
		{
			map.put("message", "fail");
			map.put("username", null);
			map.put("role", null);
			return map;
		}
	}
	
}
