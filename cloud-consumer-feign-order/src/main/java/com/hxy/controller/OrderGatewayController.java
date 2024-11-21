package com.hxy.controller;

import com.hxy.apis.PayFeignApi;
import com.hxy.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderGatewayController {

    @Autowired
    private PayFeignApi payFeignApi;

    @GetMapping("feign/pay/gateway/get/{id}")
    public Result getById(@PathVariable Integer id){
        return payFeignApi.getById(id);
    }

    @GetMapping("feign/pay/gateway/info")
    public String getGatewayInfo(){
        return payFeignApi.getGatewayInfo();
    }
}
