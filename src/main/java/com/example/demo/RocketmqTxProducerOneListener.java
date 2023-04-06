package com.example.demo;

import org.apache.rocketmq.spring.annotation.RocketMQTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionState;
import org.springframework.messaging.Message;

@RocketMQTransactionListener(rocketMQTemplateBeanName = "rocketmqTxProducerOne")
public class RocketmqTxProducerOneListener implements RocketMQLocalTransactionListener {
    @Override
    public RocketMQLocalTransactionState executeLocalTransaction(Message msg, Object arg) {
        System.out.println("RocketmqTxProducerOneListener.executeLocalTransaction");
        System.out.println("RocketmqTxProducerOneListener"+msg);
        return RocketMQLocalTransactionState.COMMIT;
    }

    @Override
    public RocketMQLocalTransactionState checkLocalTransaction(Message msg) {
        System.out.println("RocketmqTxProducerOneListener.checkLocalTransaction");
        System.out.println("RocketmqTxProducerOneListener"+msg);
        return RocketMQLocalTransactionState.COMMIT;
    }
}
