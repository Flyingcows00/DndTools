package com.dnd.resources;

import com.dnd.dao.UserDao;
import com.dnd.model.User;
import com.dnd.model.UsersResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("users")
@CrossOrigin
public class UserResource {

    @Autowired
    private UserDao dao;

    @GetMapping
    public UsersResponse getUsers() {
        UsersResponse users = new UsersResponse();
        users.setUsers(dao.getUsers());
        return users;
    }

    @GetMapping("/{username}")
    public User getuser(@PathVariable String username) {
        return dao.getUser(username);
    }

}
