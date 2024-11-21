package com.hxy.controller;

import com.hxy.apis.PayFeignSentinelApi;
import com.hxy.utils.Result;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("consumer")
public class OrderNacosController {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${service-url.nacos-user-service}")
    private String serverURL;

    @GetMapping("pay/nacos/{id}")
    public String paymentInfo(@PathVariable Integer id) {
        String result = restTemplate.getForObject(serverURL + "/pay/nacos/" + id, String.class);
        return result + "\t" + " I am OrderNacosController83调用者";

    }

    //========================================
    @Resource
    private PayFeignSentinelApi payFeignSentinelApi;

    @GetMapping("pay/nacos/get/{orderNo}")
    public Result getPayByOrderNo(@PathVariable String orderNo){
        return payFeignSentinelApi.getPayOrderNo(orderNo);
    }
}
