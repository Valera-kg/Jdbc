package com.example.springJpaRepository.service;

import com.example.springJpaRepository.model.User;
import com.example.springJpaRepository.repository.UserRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

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

