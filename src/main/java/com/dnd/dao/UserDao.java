package com.dnd.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDao {

    @Autowired
    private String addUser;
    @Autowired
    private String removeUser;

}
