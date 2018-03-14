package com.example.springboot.exception;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * URL不存在的异常处理
 */
@RestController
public class LossController implements ErrorController{
    @Override
    public String getErrorPath(){
        return "/error";
    }

    @RequestMapping(value = "/error")
    public Object error(HttpServletResponse resp,HttpServletRequest req){
        // 错误处理逻辑
        return "其他异常";
    }


}
