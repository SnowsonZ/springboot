package com.example.aop.demo;

import com.example.aop.demo.product.ProductService;
import com.example.aop.demo.product.entity.Product;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

    @Autowired
    private ProductService productService;

    @Test
    public void insertOneTest() {

        productService.insertOne(new Product(123456L, "砰砰博士卡包*80"));
    }

}
