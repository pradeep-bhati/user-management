package com.iqvia.myapplication;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


public class TestClass {
	
	PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	
	@Test
	public void printPassword() {
		String encoded = passwordEncoder.encode("root");
		System.out.println(encoded);
	}

}
