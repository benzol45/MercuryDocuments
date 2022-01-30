package com.example.mercury.repository.impl;

import com.example.mercury.entitiy.User;
import com.example.mercury.repository.UserRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class UserRepositoryImpl implements UserRepository {
    @PersistenceContext
    EntityManager entityManager;

    @Override
    @Transactional
    public void addUser(User user) {
        entityManager.persist(user);
    }

    @Override
    public User getUserByUsername(String userName) {
        return (User) entityManager.createQuery("SELECT user from User as user where user.username=:UserName")
                .setParameter("UserName",userName).getSingleResult();
    }

    @Override
    public int getCount() {
        return ((Long)entityManager.createQuery("SELECT count(user) from User as user").getSingleResult()).intValue();
    }
}
