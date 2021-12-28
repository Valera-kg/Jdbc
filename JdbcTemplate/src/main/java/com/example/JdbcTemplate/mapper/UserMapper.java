package com.example.JdbcTemplate.mapper;

import com.example.JdbcTemplate.domain.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements RowMapper<User> {
    public User mapRow(ResultSet resultSet, int i) throws SQLException {
        User user = new User();
        user.setId(resultSet.getInt("person_id"));
        user.setName(resultSet.getString("name"));
        return user;
    }
}
