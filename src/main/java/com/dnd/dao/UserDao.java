package com.dnd.dao;

import com.dnd.model.User;
import com.dnd.model.adapter.UserRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class UserDao {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;
    @Autowired
    private DataSource dataSource;
    @Autowired
    private String addUser;
    @Autowired
    private String removeUser;
    @Autowired
    private String getUsers;
    @Autowired
    private String getUser;

    public List<User> getUsers() {
        return jdbcTemplate.query(getUsers, new UserRowMapper());
    }

    public User getUser(String username) {
        Map<String, String> params = new HashMap<>();
        params.put("username", username);
        return jdbcTemplate.queryForObject(getUser, params, new UserRowMapper());
    }

}
