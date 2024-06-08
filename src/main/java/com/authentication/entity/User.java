package com.authentication.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "userDetails")
public class User 
{
	@Id
	@Column(length = 255)
	private String username;
	@Column(length = 255, unique = false, nullable = false)
	private String name;
	@Column(length = 255, unique = false, nullable = false)
	private String address;
	@Column(unique = true, nullable = false)
	private long mobile;
	@Column(unique = true, nullable = false, length = 150)
	private String mail;
	@Column(length = 255, nullable = false, unique = false)
	private String password;
	@Column(length = 255, nullable = false, unique = false)
	private String role;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public long getMobile() {
		return mobile;
	}
	public void setMobile(long mobile) {
		this.mobile = mobile;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	
	
}
