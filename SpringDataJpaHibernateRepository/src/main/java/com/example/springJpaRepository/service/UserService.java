package com.example.springJpaRepository.service;

import com.example.springJpaRepository.model.User;

import java.util.List;

public interface UserService {
    void create(User user);
    List<User> readAll();
    User readById(User user);
    void update(User user);
    void deleteById(User user);
    void deleteAll();
}
