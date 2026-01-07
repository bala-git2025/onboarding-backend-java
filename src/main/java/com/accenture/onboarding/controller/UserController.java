package com.accenture.onboarding.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.accenture.onboarding.Service.UserService;
import com.accenture.onboarding.model.LoginResponse;
import com.accenture.onboarding.model.User;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

	@GetMapping("/health")
	public String health() {
		return "Health is running! fine";
	}

	@PostMapping("/login")
	public ResponseEntity<LoginResponse> login(@RequestBody User request) {

		User user = userService.validateLogin(request.getUsername(), request.getPassword());

		return ResponseEntity.ok(new LoginResponse("Welcome" + user.getUsername(), user.getUsername(), true));
	}

	private final UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}
}

