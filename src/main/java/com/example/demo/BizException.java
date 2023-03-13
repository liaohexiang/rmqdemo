package com.example.demo;

import org.aspectj.bridge.Message;

public class BizException extends RuntimeException {

    public BizException(String messsage){
        super(messsage);
    }
}
