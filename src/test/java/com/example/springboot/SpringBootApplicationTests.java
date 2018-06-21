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
import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootApplicationTests {

    @Autowired
    StringRedisTemplate stringRedisTemplate;
    private Logger logger;

    @Test
    public void test() {
        List<String> list = new ArrayList<>();
        list.add("to");
        list.add("JayChou's");
        list.add("concert");
        stringRedisTemplate.opsForValue().set("timetest", "time losing", 10, TimeUnit.SECONDS);
        stringRedisTemplate.opsForValue().set("productName", "concert_ticket");
        stringRedisTemplate.opsForList().leftPushAll("qq", list); // 向redis存入List
        stringRedisTemplate.opsForList().range("qq", 2, 0).forEach(value -> logger.debug(value));
    }

    @Before
    public void before() {
        logger = LoggerFactory.getLogger(SpringBootApplicationTests.class);
    }

    @Test
    public void contextLoads() {
    }

}
