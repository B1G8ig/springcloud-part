package com.hxy.controller;

import cn.hutool.core.date.DateUtil;
import com.hxy.apis.PayFeignApi;
import com.hxy.pojo.PayDTO;
import com.hxy.utils.Result;
import com.hxy.utils.ResultCodeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping
public class OrderController {
    @Autowired
    private PayFeignApi payFeignApi;

    @PostMapping("feign/pay/add")
    public Result addOrder(@RequestBody PayDTO payDTO){
        Result result = payFeignApi.addPay(payDTO);
        return result;
    }
    @GetMapping("feign/pay/get/{id}")
    public Result getPayInfo(@PathVariable Integer id){
        Result payById = null;
        //try{
            //System.out.println("调用开始-----: "+ DateUtil.now());
            payById = payFeignApi.getPayById(id);

        //}catch (Exception e){
           // e.printStackTrace();
           // System.out.println("调用结束-----: "+ DateUtil.now());
            //payById = Result.build(null, ResultCodeEnum.RC500.getCode(), e.getMessage());
        //}
        return payById;
    }
    @GetMapping("feign/pay/mylb")
    public String mylb(){
        return payFeignApi.mylb();
    }

}
