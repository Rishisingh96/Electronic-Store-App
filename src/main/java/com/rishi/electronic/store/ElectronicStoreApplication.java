package com.rishi.electronic.store;


import com.rishi.electronic.store.config.AppConstants;
import com.rishi.electronic.store.entites.Role;
import com.rishi.electronic.store.entites.User;
import com.rishi.electronic.store.repositories.RoleRepository;
import com.rishi.electronic.store.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.UUID;

@SpringBootApplication
public class ElectronicStoreApplication  implements CommandLineRunner {

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	UserRepository userRepository;

	@Autowired
	PasswordEncoder passwordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(ElectronicStoreApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Role roleAdmin = roleRepository.findByName("ROLE_"+ AppConstants.ROLE_ADMIN).orElse(null);
		if(roleAdmin == null) {
			Role role1 = new Role();
			role1.setRoleId(UUID.randomUUID().toString());
			role1.setName("ROLE_"+ AppConstants.ROLE_ADMIN);
			roleRepository.save(role1);
		}
		Role roleNormal = roleRepository.findByName("ROLE_"+ AppConstants.ROLE_NORMAL).orElse(null);
		if(roleNormal ==null){
			Role role2 = new Role();
			role2.setRoleId(UUID.randomUUID().toString());
			role2.setName("ROLE_"+ AppConstants.ROLE_NORMAL);
			roleRepository.save(role2);
		}

		//ek admin user banaunga
		User user = userRepository.findByEmail("rishi@gmail.com").orElse(null);
		if(user==null){
			 user = new User();

			 user.setName("rishi singh");
			 user.setEmail("rishi@gmail.com");
			 user.setPassword(passwordEncoder.encode("rishi"));
			 user.setRoles(List.of(roleAdmin));
			 user.setUserId(UUID.randomUUID().toString());

			userRepository.save(user);

		}
	}
}
