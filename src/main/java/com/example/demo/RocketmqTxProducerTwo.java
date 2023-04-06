package com.example.demo;

import org.apache.rocketmq.spring.annotation.ExtRocketMQTemplateConfiguration;
import org.apache.rocketmq.spring.core.RocketMQTemplate;

/**
 * 定义一个事务消息的RocketMQTemplate
 */
@ExtRocketMQTemplateConfiguration(group = "${txMessage.producerTwo.group}",enableMsgTrace = true)
public class RocketmqTxProducerTwo extends RocketMQTemplate {
}
