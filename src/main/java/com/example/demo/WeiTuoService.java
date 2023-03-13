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
                return false;
            }
            weiTuoDBTransactionalService.createWeiTuo(bizVo);
            return true;
        }catch (Exception e){
            // 记录业务异常,需要有一个定时任务在轮询异常，定时任务里调用冲正接口保证一定冲正成功
            weiTuoDBTransactionalService.logBizException(bizVo);
            // 发送冲正消息
            mqProducer.send((Message) bizVo);
            return false;
        }
    }
}
