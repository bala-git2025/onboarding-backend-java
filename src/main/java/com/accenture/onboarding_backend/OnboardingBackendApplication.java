package com.accenture.onboarding_backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.persistence.autoconfigure.EntityScan;

@SpringBootApplication
@EntityScan("com.accenture.onboarding_model")
public class OnboardingBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(OnboardingBackendApplication.class, args);
	}

}
