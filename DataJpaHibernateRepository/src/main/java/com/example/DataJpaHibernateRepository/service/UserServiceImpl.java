package com.example.DataJpaHibernateRepository.service;

import com.example.DataJpaHibernateRepository.domain.User;
import com.example.DataJpaHibernateRepository.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;


@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    public void create(User user) {
        userRepository.save(user);
    }
    
    public List<User> readAll() {
        return userRepository.findAll();
    }

    public User readById(User user) {
        return userRepository.findById(user.getId()).orElse(null);
    }

    public void update(User user) {
        userRepository.saveAndFlush(user);
    }

    public void deleteById(User user) {
       userRepository.deleteById(user.getId());
    }

    @Transactional //for custom Query (updateSeq)
    public void deleteAll() {
        userRepository.deleteAll();
        userRepository.updateSeq();
    }

}

