package com.example.demo;

import org.apache.rocketmq.spring.core.RocketMQLocalTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionState;
import org.springframework.messaging.Message;

public class MyTransListener implements RocketMQLocalTransactionListener {
    @Override
    public RocketMQLocalTransactionState executeLocalTransaction(Message msg, Object arg) {
        return null;
    }

    @Override
    public RocketMQLocalTransactionState checkLocalTransaction(Message msg) {
        return null;
    }


}
