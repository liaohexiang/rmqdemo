package com.example.demo;


import org.springframework.integration.json.ObjectToJsonTransformer;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class WeiTuoDBTransactionalService {

    @Transactional
    public void createWeiTuo(Object weiTuoVO)
    {
        //insert......
        //update .....

    }

    public void logBizException(Object weiTuoVo){
        //insert biz info to log table.....
    }
}
