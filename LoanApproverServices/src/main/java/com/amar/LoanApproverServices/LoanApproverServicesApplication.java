package com.amar.LoanApproverServices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan("com.amar")
@EnableJpaRepositories("com.amar.repository")
@EntityScan("com.amar")
public class LoanApproverServicesApplication {

	public static void main(String[] args) {
		SpringApplication.run(LoanApproverServicesApplication.class, args);
		System.out.println("LoanApprover Service running...");
	}

}
