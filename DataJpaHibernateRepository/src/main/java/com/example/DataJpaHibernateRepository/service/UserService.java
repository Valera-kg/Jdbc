package com.example.DataJpaHibernateRepository.service;

import com.example.DataJpaHibernateRepository.domain.User;

import java.util.List;

public interface UserService {
    public void create(User user);
    public List<User> readAll();
    public User readById(User user);
    public void update(User user);
    public void deleteById(User user);
    public void deleteAll();
}
