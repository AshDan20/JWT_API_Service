package com.poc.jwtapi.JWTApiService;

import com.poc.jwtapi.JWTApiService.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class JwtApiServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(JwtApiServiceApplication.class, args);
	}
	@Bean
	CommandLineRunner init(UserService userService) {
		return (args) -> {
			userService.init();
		};
	}
}
