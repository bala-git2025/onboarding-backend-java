package com.accenture.onboarding.serviceImpl;

import org.springframework.stereotype.Service;

import com.accenture.onboarding.Dao.UserDao;
import com.accenture.onboarding.DaoImpl.UserRepository;
import com.accenture.onboarding.Exception.OnboardingException;
import com.accenture.onboarding.Service.UserService;
import com.accenture.onboarding.model.User;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository; // Standard queries
    private final UserDao userDao;               // Custom queries

    public UserServiceImpl(UserRepository userRepository, UserDao userDao) {
        this.userRepository = userRepository;
        this.userDao = userDao;
    }

    @Override
    public User validateLogin(String username, String password) {
        // Use Spring Data JPA first
        User user = userRepository.findByUsername(username)
                                  .orElse(null);

        // Fallback to custom DAO if needed
        if (user == null) {
            user = userDao.findByUsernameCustom(username);
        }

        if (user == null) {
            throw new OnboardingException("ERR-022", "User not found");
        }

        if (!user.getPassword().equals(password)) {
            throw new OnboardingException("ERR-023", "Invalid Password");
        }

        return user;
    }
}
