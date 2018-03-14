package com.example.springboot.controller;

import com.example.springboot.model.User;
import com.example.springboot.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController{

    @Autowired
    private UserService userServiceImp;

    @GetMapping("/{id}")
    public User findById(@PathVariable(value = "id") long id){
        return userServiceImp.findUserById(id);
    }

    @PostMapping("/add")
    public String addUser(@RequestParam String name,@RequestParam String password){
        User user = new User();
        user.setName(name);
        user.setPassword(password);
        userServiceImp.insertUser(user);
        return "add successful";
    }

    @GetMapping("/all")
    public List<User> findAllUser(){
        return userServiceImp.findAllUser();
    }
}
