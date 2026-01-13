package com.accenture.onboarding.DaoImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.accenture.onboarding.Dao.UserDao;
import com.accenture.onboarding.Exception.OnboardingException;
import com.accenture.onboarding.model.User;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.PersistenceException;
import jakarta.persistence.TypedQuery;

@Repository
public class UserDaoImpl implements UserDao {

    private static final Logger log =
            LoggerFactory.getLogger(UserDaoImpl.class);

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public User findByUsernameCustom(String username) {

        log.debug("Fetching user from DB for username: {}", username);

        try {
            TypedQuery<User> query = entityManager.createQuery(
                "SELECT u FROM User u WHERE u.usernamehjsakg = :username",
                User.class
            );
            
          log.info(username);
            query.setParameter("username", username);

            return query.getResultStream()
                        .findFirst()
                        .orElse(null); 

        } catch (PersistenceException ex) {

            log.error("Database error while fetching user: {}", username, ex);
            throw new OnboardingException(
                "Database error occurred while fetching user",ex.getMessage()
            );
        }
    }
}
