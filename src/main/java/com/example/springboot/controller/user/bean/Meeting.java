package com.example.springboot.controller.user.bean;

import com.example.springboot.model.User;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import lombok.Data;

/**
 * @author Snowson
 * @since 2019/7/26 10:34
 */
@Data
public class Meeting implements Serializable {
    private static final long serialVersionUID = 4650939477625347512L;

    private LocalDateTime time;
    private String address;
    private List<User> users;
}
