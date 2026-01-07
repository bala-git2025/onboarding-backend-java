package com.accenture.onboarding.Exception;

public class OnboardingException extends RuntimeException {
	private String code;

	public OnboardingException(String code, String message) {
		super(message);
		this.code = code;
	}

	public String getCode() {
		return code;
	}
}
