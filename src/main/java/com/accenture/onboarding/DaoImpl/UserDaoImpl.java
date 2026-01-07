package com.accenture.onboarding.DaoImpl;

import org.springframework.stereotype.Repository;

import com.accenture.onboarding.Dao.UserDao;
import com.accenture.onboarding.model.User;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;
    
    UserRepository userRepository;

    @Override
    public User findByUsernameCustom(String username) {
        TypedQuery<User> query = entityManager.createQuery(
            "SELECT u FROM User u WHERE u.username = :username", User.class);
        query.setParameter("username", username);
        return query.getResultStream().findFirst().orElse(null);
    }
}
