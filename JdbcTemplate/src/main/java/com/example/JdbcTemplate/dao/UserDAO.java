package com.example.JdbcTemplate.dao;

import com.example.JdbcTemplate.domain.User;
import com.example.JdbcTemplate.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserDAO {
    @Autowired
    public JdbcTemplate jdbcTemplate;

    public void create(User user) {
        jdbcTemplate.update("insert into persondb (name) values (?);", user.getName());
    }

    public List<User> readAll() {
        return jdbcTemplate.query("select * from persondb;", new UserMapper());
    }

    public User readById(User user) {
        return jdbcTemplate.queryForObject("select * from persondb where person_id =?;", new UserMapper(), user.getId());
    }

    public void update(User user) {
        jdbcTemplate.update("update persondb set name=? where person_id=?;", user.getName(), user.getId());
    }

    public void deleteById(User user) {
        jdbcTemplate.update("delete from persondb where person_id=?;", user.getId());
    }

    public void deleteAll() {
        jdbcTemplate.update("delete from persondb ;\n" + "alter sequence persondb_person_id_seq restart;");
    }

}

