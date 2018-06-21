package com.example.springboot.controller;

import com.example.springboot.exception.ResourceNotFound;
import com.example.springboot.model.User;
import com.example.springboot.model.Users;
import com.example.springboot.service.UserService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    Logger logger = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserService userServiceImp;

    @GetMapping("/{id}")
    public User findById(@PathVariable(value = "id") long id) throws ResourceNotFound {

        User user = userServiceImp.findUserById(id);
        if (user == null) {
            throw new ResourceNotFound(" user with id : " + id);
        }
        return user;
    }

    @PostMapping("/add")
    public String addUser(@RequestParam String name, @RequestParam String password) {
        User user = new User();
        user.setName(name);
        user.setPassword(password);
        userServiceImp.insertUser(user);
        return "add successful";
    }

    @GetMapping("/all")
    public List<User> findAllUser() {
        return userServiceImp.findAllUser();
    }

    @GetMapping("/test")
    public int createError() {
        logger.debug("test info");
        return 2 / 0;
    }

    //使用RequestBody可以传List<Object>
    @PostMapping("/list")
    public String addUsers(@RequestBody ArrayList<User> users) {
        userServiceImp.insertUsers(users);
        return "add successful";
    }
}
