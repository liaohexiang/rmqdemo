package com.example.demo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("order")
public class TestController1 {

    @Autowired
    ToucunService toucunService;

    @GetMapping("toucun-balance-create")
    public @ResponseBody String testTxProducerOne(@RequestBody Object obj)  {

        toucunService.createTouCunBalance(obj);
        return "success";
    }
}
