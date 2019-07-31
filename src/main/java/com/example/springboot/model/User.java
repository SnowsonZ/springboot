package com.example.springboot.model;

import java.io.Serializable;

import lombok.Data;

@Data
public class User implements Serializable {
    private static final long serialVersionUID = 5955097707966489630L;
    private int id;
    private String name;
    private String password;
    private String avatar;
}
