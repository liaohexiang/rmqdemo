package com.example.demo;

import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Service;

@Service
@RocketMQMessageListener(consumerGroup = "${consumer.test1Consumer}",topic = "${consumer.test1Topic}",enableMsgTrace = true)
public class Test1Consumer implements RocketMQListener<String> {
    @Override
    public void onMessage(String message) {
        /**
         * NOTE:
         * 1.消费要实现幂等
         * 2.异常不要catch住，消息中间件会重发消息保证消息的消费成功(at least once)
         */
        System.out.println("Test1Consumer is consuming message: "+message);
    }
}
