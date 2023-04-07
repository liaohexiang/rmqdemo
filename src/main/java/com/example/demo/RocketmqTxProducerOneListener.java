package com.example.demo;

import org.apache.rocketmq.spring.annotation.RocketMQTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;

@RocketMQTransactionListener(rocketMQTemplateBeanName = "rocketmqTxProducerOne")
public class RocketmqTxProducerOneListener implements RocketMQLocalTransactionListener {

    @Autowired
    ToucunService toucunService;

    @Override
    public RocketMQLocalTransactionState executeLocalTransaction(Message msg, Object arg) {
        System.out.println("RocketmqTxProducerOneListener.executeLocalTransaction"+msg);
        /**
         * NOTE:数据库事务要在这里调用
         */
        toucunService.save2DBWithMsgTxID(msg,arg);
        return RocketMQLocalTransactionState.COMMIT;

    }

    @Override
    public RocketMQLocalTransactionState checkLocalTransaction(Message msg) {
        System.out.println("RocketmqTxProducerOneListener.checkLocalTransaction"+msg);
        boolean exists = toucunService.getTranStatusByTxID(msg);
        return exists? RocketMQLocalTransactionState.COMMIT : RocketMQLocalTransactionState.ROLLBACK;
    }
}
