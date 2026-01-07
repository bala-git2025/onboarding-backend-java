package com.accenture.onboarding.Service;

import com.accenture.onboarding.model.User;

public interface UserService {
    User validateLogin(String username, String password);
}
