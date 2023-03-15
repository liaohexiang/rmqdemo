package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.integration.json.ObjectToJsonTransformer;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class TouCunRemoteService {

    @Autowired
    private RestTemplate restTemplate;
    //调用头寸占用接口
    public ResponseEntity<Boolean> occupy(Object occupyVo){

        /*这里面不要有try-catch,不要把异常吃掉了，吃掉异常无法知道调用成功还是失败*/
       return  restTemplate.postForEntity("http：//toucun/occupy", occupyVo, Boolean.class);
    }

    //调用恢复头寸占用接口
    public ResponseEntity<Boolean> reverseOccupy(Object reverseOccupyVo) {

        /*这里面不要有try-catch,不要把异常吃掉了，吃掉异常无法知道调用成功还是失败*/
        return restTemplate.postForEntity("http：//toucun/reverse-occupy", reverseOccupyVo, Boolean.class);
    }
}
