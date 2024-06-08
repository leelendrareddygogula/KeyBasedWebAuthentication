package com.authentication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class KeyBasedAuthenticationApplication {

	public static void main(String[] args) {
		SpringApplication.run(KeyBasedAuthenticationApplication.class, args);
		System.out.println("Project Running Successfully");
	}

}
