package com.authentication.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "keyDetails")
public class Key 
{
	@Id
	@Column(length = 10)
	private String keyIdentifier;  //Stores the ending characters of username (6 or 7 characters)
	@Column(unique = true, nullable = false, length = 200)
	private String uniqueKey;
	@Column(nullable = false, unique = false)
	private int additionalCharacters;
	@Column(nullable = false, unique = false, length = 200)
	private String salt;
	
	public String getKeyIdentifier() {
		return keyIdentifier;
	}
	public void setKeyIdentifier(String keyIdentifier) {
		this.keyIdentifier = keyIdentifier;
	}
	public String getUniqueKey() {
		return uniqueKey;
	}
	public void setUniqueKey(String uniqueKey) {
		this.uniqueKey = uniqueKey;
	}
	public int getAdditionalCharacters() {
		return additionalCharacters;
	}
	public void setAdditionalCharacters(int additionalCharacters) {
		this.additionalCharacters = additionalCharacters;
	}
	public String getSalt() {
		return salt;
	}
	public void setSalt(String salt) {
		this.salt = salt;
	}
	
}
