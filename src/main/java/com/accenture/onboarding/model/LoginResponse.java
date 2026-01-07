package com.accenture.onboarding.model;




public class LoginResponse {
    private String message;
    private String username;
    private boolean success;

    public LoginResponse() {}

    public LoginResponse(String message, String username, boolean success) {
        this.message = message;
        this.username = username;
        this.success = success;
    }

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

  
}

