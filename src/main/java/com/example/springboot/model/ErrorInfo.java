package com.example.springboot.model;

public class ErrorInfo{
    private String error;

    public ErrorInfo(String error){
        this.error = error;
    }

    public String getError(){
        return error;
    }

    public void setError(String error){
        this.error = error;
    }
}
