package com.example.demo;

import org.apache.rocketmq.spring.support.RocketMQHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ToucunService {

    @Autowired
    private RocketmqTxProducerOne txProducerOne;

    @Autowired
    private RocketmqTxProducerTwo txProducerTwo;

    public void createTouCunBalance(Object bizObject){
        /**
         * 做些业务操作......
         */
        txProducerOne.sendMessageInTransaction("TOUCUN_BALANCE_CREATE", MessageBuilder.withPayload("{TOUCUN_BALANCE_CREATE}").build(),null);
    }

    @Transactional
    public void save2DBWithMsgTxID(Message msg, Object bizObject){
        /**
         * update ....
         * insert.....
         * NOTE:把消息的“TRANSACTION_ID”保存到数据库,获取方式：String tx_id = (String)msg.getHeaders().get(RocketMQHeaders.TRANSACTION_ID);
         */


    }
    public boolean getTranStatusByTxID(Message msg){
        String tx_id = (String)msg.getHeaders().get(RocketMQHeaders.TRANSACTION_ID);
        /**
         * 如果数据库里存在“tx_id”，return TRUE，else return FALSE
         */
        return true;
    }
}
