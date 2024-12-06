package com.amar.AdminServices;

import jakarta.persistence.Entity;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan("com.amar.model")
@ComponentScan("com.amar")
@EnableJpaRepositories("com.amar.repository")
public class AdminServicesApplication {

	public static void main(String[] args) {
		SpringApplication.run(AdminServicesApplication.class, args);
		System.out.println("Admin Service running...");
	}
}
