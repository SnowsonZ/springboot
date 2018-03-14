package com.example.springboot.dao;

import com.example.springboot.model.User;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface UserDao{
    void insertUser(@Param("user") User user);

    User findUserById(@Param("id") long id);

    List<User> findAllUser();
}
