package com.example.usermanage.repository;

import com.example.usermanage.entity.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
public class UserRepository {

    @PersistenceContext //entity 매니저 주입
    private EntityManager em;

    public void save(User user){
        em.persist(user);
    }

    public User findOne(String userId){
        return em.find(User.class, userId);
    }

}
