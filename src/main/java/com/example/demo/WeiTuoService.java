package com.example.demo;


import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class WeiTuoService {

    @Autowired
    TouCunRemoteService touCunRemoteService;

    @Autowired
    RiskRemoteService riskRemoteService;

    @Autowired
    WeiTuoDBTransactionalService weiTuoDBTransactionalService;

    @Autowired
    DefaultMQProducer mqProducer;
    public boolean createWeiTuo(Object bizVo) throws Exception
    {
        try{
            boolean riskResult = riskRemoteService.occupy(bizVo).getBody();
            if (riskResult == false) return false;
            boolean toucunResult = touCunRemoteService.occupy(bizVo).getBody();
            if (toucunResult == false){
                throw new BizException("头寸业务失败,风控需要回滚");
            }
            weiTuoDBTransactionalService.createWeiTuo(bizVo);
            return true;
        }catch (Exception e){

            try {
                //发送冲正消息,消息客户端里面有重试机制
                mqProducer.send((Message) bizVo,2000L);
            }catch (Exception exception){
                // 发送消息失败，记录业务异常,需要有一个定时任务在轮询异常并重发消息或调用接口
                //bizVO 可以序列化成json字符串保存到异常记录表供后续消息重试或接口使用
                weiTuoDBTransactionalService.logBizException(bizVo);
            }
            return false;
        }
    }

    public boolean createWeiTuoWithUK(){

    }
    public boolean createWeiTuoWithToken(){

    }
}
