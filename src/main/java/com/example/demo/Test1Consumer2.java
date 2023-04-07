package com.example.demo;

import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Service;

@Service
@RocketMQMessageListener(consumerGroup = "DEMO_TEST1_CONSUMER2",topic = "TEST1",enableMsgTrace = true)
public class Test1Consumer2 implements RocketMQListener<String> {
    @Override
    public void onMessage(String message) {
        System.out.println("DEMO_TEST1_CONSUMER Test1Consumer2 is consuming message: "+message);
    }
}
