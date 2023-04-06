package com.example.demo;

import org.apache.rocketmq.spring.annotation.ExtRocketMQTemplateConfiguration;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Value;

/**
 * 定义一个事务消息的RocketMQTemplate
 */
@ExtRocketMQTemplateConfiguration(group = "${txMessage.producerOne.group}",enableMsgTrace = true)
public class RocketmqTxProducerOne extends RocketMQTemplate {

}
