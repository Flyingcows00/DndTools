package com.dnd.dao;

import com.dnd.model.User;
import com.dnd.model.adapter.UserRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@Transactional
public class UserDao {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;
    private static final String ADD_USER = "INSERT INTO users(username) VALUES(:username);";
    private static final String REMOVE_USER = "DELETE FROM users WHERE username=:username LIMIT ONE ROW;";
    private static final String GET_USERS = "SELECT username, admin FROM users;";
    private static final String GET_USER = "SELECT username, admin FROM users WHERE username = :username;";

    public List<User> getUsers() {
        return jdbcTemplate.query(GET_USERS, new UserRowMapper());
    }

    public User getUser(String username) {
        Map<String, String> params = new HashMap<>();
        params.put("username", username);
        return jdbcTemplate.queryForObject(GET_USER, params, new UserRowMapper());
    }

}
