package com.example.demo;

import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class RocketmqConsumer {

    @StreamListener(ConsumerConfiguration.ConsumerSource.DEMO1_INPUT)
    public void consumeDemo1(@Payload Object obj){

        System.out.println(obj);
    }

    @StreamListener(ConsumerConfiguration.ConsumerSource.DEMO2_INPUT)
    public void consumeDemo2(@Payload Object obj){

        System.out.println(obj);
    }
}
