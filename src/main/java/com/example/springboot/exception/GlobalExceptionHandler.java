package com.example.springboot.exception;

import com.example.springboot.model.ParamError;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * 正确输入URL到进入逻辑前异常处理，eg:入参类型错误
 */
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler{

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex,Object body,
                                                             HttpHeaders headers,
                                                             HttpStatus status,WebRequest request){
        if(ex instanceof MethodArgumentTypeMismatchException){
            MethodArgumentTypeMismatchException exception = (MethodArgumentTypeMismatchException)ex;
            return new ResponseEntity<Object>(new ParamError(exception.getName()
                    ,exception.getMessage()),HttpStatus.NOT_EXTENDED);
        }
        return super.handleExceptionInternal(ex,body,headers,status,request);

    }
}
