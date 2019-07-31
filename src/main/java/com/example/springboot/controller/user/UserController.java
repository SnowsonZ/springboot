package com.example.springboot.controller.user;

import com.alibaba.fastjson.JSON;
import com.example.springboot.controller.user.bean.Meeting;
import com.example.springboot.exception.ResourceNotFound;
import com.example.springboot.model.User;
import com.example.springboot.model.UserApp;
import com.example.springboot.service.user.UserService;
import com.example.springboot.utils.FileUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {
    Logger logger = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserService userServiceImp;
    @Autowired
    private StringRedisTemplate redisTemplate;

    @GetMapping("/{id}")
    public User findById(@PathVariable(value = "id") long id) throws ResourceNotFound {

        User user = userServiceImp.findUserById(id);
        if (user == null) {
            throw new ResourceNotFound(" user with id : " + id);
        }
        return user;
    }

    @PostMapping("/add")
    public @ResponseBody
    String addUser(@RequestParam String name, @RequestParam String password,
                   @RequestParam("avatar") MultipartFile file) {
        //修改图片名称
        String fileName = "avatar." + file.getOriginalFilename().split("\\.")[1];
        long img_key = System.currentTimeMillis();
        String filePath = FileUtils.PATH_IMG_UPLOAD + img_key;
        try {
            FileUtils.uploadFile(file.getBytes(), filePath, fileName);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("获取文件字节码出错");
        }
        User user = new User();
        user.setName(name);
        user.setPassword(password);
        user.setAvatar(FileUtils.URL_IMG_UPLOAD + img_key + "/" + fileName);
        userServiceImp.insertUser(user);
//        redisTemplate.opsForValue().set(user.getPassword(), user.getName(), 2, TimeUnit.HOURS);
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

    @GetMapping("/accessToken")
    public String getAccessToken(@RequestParam long id) {
        User user = userServiceImp.findUserById(id);
        return redisTemplate.opsForValue().get(user.getPassword());
    }

    @GetMapping("/back")
    public String back() {
        return null;
    }

    @PostMapping("param")
    public String param(@RequestParam("name") String name) {
        return name;
    }


    /**
     * 测试忽略序列化字段
     *
     * @return the user app
     */
    @GetMapping("transient")
    public UserApp transientTest() {
        UserApp userApp = new UserApp();
        userApp.setName("OA");
        userApp.setPreferRespTime("1234");
        log.info("userApp: {}", JSON.toJSONString(userApp));

        return userApp;
    }

    /**
     * 测试 LocalDateTime
     *
     * @param meeting the meeting
     * @return meeting
     */
    @PostMapping("meeting")
    public Meeting doMeeting(@RequestBody Meeting meeting) {
        log.info("{}", JSON.toJSONString(meeting));
        return meeting;
    }

    /**
     * 测试 SerializationFeature.FAIL_ON_EMPTY_BEANS
     *
     * @return test
     */
    @GetMapping("info")
    public Test doTest() {
        Product product = new Product();
        product.setName("product");
        return new Test(product);
    }
}

@Data
@AllArgsConstructor
@NoArgsConstructor
class Test {
    Product p;
}

@Data
class Product {
    String name;
}
