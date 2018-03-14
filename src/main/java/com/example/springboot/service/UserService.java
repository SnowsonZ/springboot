package com.example.springboot.service;

import com.example.springboot.model.User;

import java.util.List;

public interface UserService{
    User findUserById(long userId);

    void insertUser(User user);

    List<User> findAllUser();
}
