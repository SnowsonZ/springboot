package com.example.aop.demo.product;

import com.example.aop.demo.product.entity.Product;

import org.springframework.stereotype.Service;

/**
 * @Author: Snowson
 * @Date: 2018/7/27 10:43
 * @Description:
 */
@Service
public class ProductService {
    public void insertOne(Product product) {
        System.out.println("insert product" + product.getName());
    }

    public void queryOne(long id) {
        System.out.println("query product by Id" + id);
    }
}
