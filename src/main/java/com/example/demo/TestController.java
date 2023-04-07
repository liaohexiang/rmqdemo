package com.example.demo;

import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
public class TestController {

    @Autowired
    private RocketmqTxProducerOne txProducerOne;

    @Autowired
    private RocketmqTxProducerTwo txProducerTwo;




    @GetMapping("testTxProducerOne")
    public @ResponseBody String testTxProducerOne()  {

        txProducerOne.sendMessageInTransaction("TOUCUN_BALANCE_CREATE", MessageBuilder.withPayload("{TOUCUN_BALANCE_CREATE}").build(),null);
        return "TOUCUN_BALANCE_CREATE success";
    }

    @GetMapping("testTxProducerTwo")
    public @ResponseBody String testTxProducerTwo()  {

        txProducerTwo.sendMessageInTransaction("TOUCUN_BALANCE_UPDATE", MessageBuilder.withPayload("{TOUCUN_BALANCE_UPDATE}").build(),null);
        return  "TOUCUN_BALANCE_UPDATE success";
    }

    @Qualifier("rocketMQTemplate")
    private RocketMQTemplate rocketMQTemplate;
    /**
     * 非事务消息
     * @return
     */
    @GetMapping("testNonTxProducer")
    public @ResponseBody String testNonTxProducer()  {

        rocketMQTemplate.syncSend("TEST1", MessageBuilder.withPayload("{TEST1}").build());
        return  "TEST1 success";
    }

}
