package com.zampo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.zampo.model.User;
import com.zampo.repository.UserRepository;

@SpringBootApplication
public class BackApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackApplication.class, args);
	}
	
	@Bean
	CommandLineRunner initDatabase(UserRepository userRepository) {
		return args -> {
			userRepository.deleteAll();

			User u = new User();
			u.setName("Lucas V A Zampoli");
			u.setEmail("teste@hotmail.com");
			u.setProfile("Manager");
			u.setStatus("Ativo");
			u.setPassword("123456");
			userRepository.save(u);
		};
	}
}

