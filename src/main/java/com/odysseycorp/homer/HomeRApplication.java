package com.odysseycorp.homer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

@SpringBootApplication
public class HomeRApplication {

	public static void main(String[] args) {
		SpringApplication.run(HomeRApplication.class, args);
	}

}
