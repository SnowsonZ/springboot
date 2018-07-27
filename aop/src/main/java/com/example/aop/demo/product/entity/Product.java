package com.example.aop.demo.product.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: Snowson
 * @Date: 2018/7/27 10:45
 * @Description:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    private long id;
    private String name;
}
