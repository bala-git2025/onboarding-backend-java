package com.accenture.onboarding;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {
	    "com.accenture.onboarding",
	    "com.accenture.onboarding.Service",          // scan your service package
	    "com.accenture.onboarding.serviceImpl",      // if separate
	    "com.accenture.onboarding.Dao",
	    "com.accenture.onboarding.DaoImpl",
	    "com.accenture.onboarding.backendController"
	})
@EnableJpaRepositories(basePackages = "com.accenture.onboarding.DaoImpl")
public class OnboardingBackendApplication {
    public static void main(String[] args) {
        SpringApplication.run(OnboardingBackendApplication.class, args);
    }
}
