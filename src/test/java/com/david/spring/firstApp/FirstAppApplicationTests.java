package com.david.spring.firstApp;

import org.assertj.core.api.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest
class FirstAppApplicationTests {

	Map<String, PasswordEncoder> encoderMap;

	@BeforeEach
	public void init(){
		encoderMap= new HashMap<>();
		encoderMap.put("bcryp", new BCryptPasswordEncoder());
		encoderMap.put("pbkdf", new Pbkdf2PasswordEncoder());
		encoderMap.put("scrypt",new SCryptPasswordEncoder());
	}


	@Test
	public void printEncoder(){
		System.out.println(new DelegatingPasswordEncoder("bcryp",encoderMap).encode("password"));
		System.out.println(new DelegatingPasswordEncoder("pbkdf",encoderMap).encode("password"));
		System.out.println(new DelegatingPasswordEncoder("scrypt",encoderMap).encode("password"));
		//{bcryp}$2a$10$EFLI1j7Zz8fcX5dZtjt9hebXSMRPu.xbSZ6JmjVYndM5hUWf1/fwS
		//{pbkdf}f1b960ddc1b14b1a0f261cf41c4afd8162b5f8031b6df4107ea75f2b8c96e3f5cc01991c3caa9d05
		//{scrypt}$e0801$hudSMPZOLYD1Xo8vJFlr2xch6TxtigHuKvdDIgwncl00VLiFI/InaPCP11NbVwvdZO3KLrYAHkitnVN8KbL/ZQ==$yn+LueaSk2em4VRFWYeNdwJKUjfXBGGydHgppYozj98=
	}

	@Test
	void testPasswordEncoderNeverReturnSame() {
		String passwordEncoded = new DelegatingPasswordEncoder("bcryp",encoderMap).encode("password");
		Assertions.assertNotEquals(passwordEncoded,new DelegatingPasswordEncoder("bcryp",encoderMap).encode("password"));
	}

	@Test
	void testMatch(){
		String passwordEncoded = new DelegatingPasswordEncoder("bcryp",encoderMap).encode("password");
		Assertions.assertTrue(new DelegatingPasswordEncoder("bcryp",encoderMap).matches("password",passwordEncoded));
	}


}
