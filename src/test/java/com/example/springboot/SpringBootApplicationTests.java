package com.example.springboot;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootApplicationTests{

    @Autowired
    StringRedisTemplate stringRedisTemplate;
    private Logger logger;

    @Test
    public void test(){
        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        list.add("v");
        stringRedisTemplate.opsForValue().set("productName","concert_ticket");
        stringRedisTemplate.opsForList().leftPushAll("qq",list); // 向redis存入List
        stringRedisTemplate.opsForList().range("range",1,2).forEach(value -> {
            logger.debug(value);
        });
    }

    @Before
    public void before(){
        logger = LoggerFactory.getLogger(SpringBootApplicationTests.class);
    }

    @Test
    public void contextLoads(){
    }

}
