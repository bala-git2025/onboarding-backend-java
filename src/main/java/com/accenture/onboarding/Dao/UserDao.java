package com.accenture.onboarding.Dao;



import com.accenture.onboarding.model.User;

public interface UserDao {
    User findByUsernameCustom(String username);
}

