package com.example.demo;

import org.apache.rocketmq.spring.annotation.RocketMQTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionState;
import org.springframework.messaging.Message;

@RocketMQTransactionListener(rocketMQTemplateBeanName = "rocketmqTxProducerTwo")
public class RocketmqTxProducerTwoListener implements RocketMQLocalTransactionListener {
    @Override
    public RocketMQLocalTransactionState executeLocalTransaction(Message msg, Object arg) {
        System.out.println("RocketmqTxProducerTwoListener.executeLocalTransaction");
        System.out.println("RocketmqTxProducerTwoListener"+msg);
        return RocketMQLocalTransactionState.UNKNOWN;
    }

    @Override
    public RocketMQLocalTransactionState checkLocalTransaction(Message msg) {
        System.out.println("RocketmqProducerListenerTwo.checkLocalTransaction");
        System.out.println("RocketmqProducerListenerTwo"+msg);
        return RocketMQLocalTransactionState.COMMIT;
    }
}
