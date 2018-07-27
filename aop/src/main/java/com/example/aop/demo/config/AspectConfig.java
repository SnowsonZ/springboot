package com.example.aop.demo.config;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @Author: Snowson
 * @Date: 2018/7/27 11:05
 * @Description:
 */
@Aspect
@Component
public class AspectConfig {
    @Pointcut("execution(public * com.example.aop.demo.product.ProductService.*(..))")
    public void matchFunction() {
    }

    @Before("matchFunction()")
    public void before() {
        System.out.println("");
        System.out.println("###before");
    }
}
