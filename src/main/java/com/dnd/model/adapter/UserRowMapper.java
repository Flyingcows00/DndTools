package com.dnd.model.adapter;

import com.dnd.model.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        User user = new User();
        user.setAdmin(rs.getBoolean("admin"));
        user.setUsername(rs.getString("username"));
        return user;
    }
}
