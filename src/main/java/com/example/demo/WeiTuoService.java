package com.example.demo;


import org.apache.commons.codec.digest.Md5Crypt;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;
import org.bouncycastle.crypto.digests.MD5Digest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

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
    /** NOTE:bizVo里面带有前端传入的token**/
    public boolean createWeiTuoWithToken(Object bizVo){
        /**
         * 根据bizVO.getToken()查询token表的状态
         * 如果是已用，按需返回业务数据，
         * 如果是可用，执行逻辑同createWeiTuo方法，NOTE:weiTuoDBTransactionalService.createWeiTuo(bizVo)
         * 里需要更新token状态，把token的状态修改为“已用”
         */
    }

    public String generateToken(Object tokenVo){
        /*生成token的方式很多，最好是带上业务含义的字段一起产生token*/
        StringBuilder builder = new StringBuilder();
        builder.append(tokenVo.getProdcode());//产品编号
        builder.append(tokenVo.getAssetCode());//资产编号
        builder.append(tokenVo.getCaseID);//场景编号
        Date currentDate = Calendar.getInstance().getTime();
        //精确到分钟，也可以精确到秒
        builder.append(new SimpleDateFormat("yyyy-MM-dd hh:mm").format(currentDate));
        String token = Md5Crypt.md5Crypt(builder.toString().getBytes());
        return token;
    }
    public String getToken(Object tokenVo){
        String token = generateToken(tokenVo);
        /**将token保存到"TOKEN表"且状态为"可用"**/
        return token;
    }
}
