package com.rishi.electronic.store;

import com.rishi.electronic.store.entites.User;
import com.rishi.electronic.store.repositories.UserRepository;
import com.rishi.electronic.store.security.JwtHelper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ElectronicStoreApplicationTests {

	@Autowired
	UserRepository userRepository;

	@Autowired
	JwtHelper jwtHelper;

	@Test
	void contextLoads() {
	}

	@Test
	void testTokens() {
		User user = userRepository.findByEmail("rishi@gmail.com").get();
		//String token = jwtHelper.generateToken(user);
		System.out.println("---------------Your start Key Tokens-------------------------------------------");
		//System.out.println(token);
		System.out.println("---------------Your end Key Tokens-------------------------------------------");

	}

}
