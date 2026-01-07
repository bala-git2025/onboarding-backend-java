package com.accenture.onboarding.DaoImpl;


import org.springframework.data.jpa.repository.JpaRepository;

import com.accenture.onboarding.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}

