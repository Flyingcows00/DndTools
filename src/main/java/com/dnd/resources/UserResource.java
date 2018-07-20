package com.dnd.resources;

import com.dnd.dao.UserDao;
import com.dnd.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("users")
@CrossOrigin
public class UserResource {

    @Autowired
    private UserDao dao;

    @GetMapping
    public List<User> getUsers() {
        return dao.getUsers();
    }

}
