package com.example.springboot.service.user;

import com.example.springboot.dao.user.UserDao;
import com.example.springboot.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImp implements UserService{
    @Autowired
    UserDao userDao;

    @Override
    public User findUserById(long userId){
        return userDao.findUserById(userId);
    }

    @Override
    public void insertUser(User user){
        userDao.insertUser(user);
    }

    @Override
    public List<User> findAllUser(){
        return userDao.findAllUser();
    }

    @Override
    public void insertUsers(List<User> users) {
        userDao.insertUsers(users);
    }
}
