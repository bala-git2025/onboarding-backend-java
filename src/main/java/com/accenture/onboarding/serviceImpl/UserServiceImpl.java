package com.accenture.onboarding.serviceImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.accenture.onboarding.Dao.UserDao;
import com.accenture.onboarding.DaoImpl.UserDaoImpl;
import com.accenture.onboarding.DaoImpl.UserRepository;
import com.accenture.onboarding.Exception.OnboardingException;
import com.accenture.onboarding.OnboardingBackendApplication;
import com.accenture.onboarding.Service.UserService;
import com.accenture.onboarding.model.User;

@Service
public class UserServiceImpl implements UserService {
	private static final Logger log =
            LoggerFactory.getLogger(UserDaoImpl.class);

    private final OnboardingBackendApplication onboardingBackendApplication;

    private final UserRepository userRepository; // Standard queries
    private final UserDao userDao;               // Custom queries

    public UserServiceImpl(UserRepository userRepository, UserDao userDao, OnboardingBackendApplication onboardingBackendApplication) {
        this.userRepository = userRepository;
        this.userDao = userDao;
        this.onboardingBackendApplication = onboardingBackendApplication;
    }

    @Override
    public User validateLogin(String username, String password) throws OnboardingException   {
    	 log.debug("Validating login for username: {}", username);
        // Use Spring Data JPA first
        User user = userRepository.findByUsername(username)
                                  .orElse(null);

        // Fallback to custom DAO if needed
          if (user == null) {
        	  log.warn("User not found for username: {}", username);
            user = userDao.findByUsernameCustom(username);
        }
     
        if (user == null) {
            throw new OnboardingException("ERR-022", "User not found");
        }

        if (!user.getPassword().equals(password)) {
        	 log.warn("Invalid password for username: {}", username);
            throw new OnboardingException("ERR-023", "Invalid Password");
        }

        return user;
    }
}
