package com.hxy.controller;

import com.hxy.pojo.PayDTO;
import com.hxy.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("consumer")
public class OrderController {
    private static final String PaymentSrv_URL = "http://cloud-payment-service";// 服务注册中心上的微服务名称
    @Autowired
    private RestTemplate restTemplate;

    @PostMapping("pay/add")
    public Result addOrder(@RequestBody PayDTO payDTO){
        return restTemplate.postForObject(PaymentSrv_URL+"/pay/add",payDTO,Result.class);
    }

    @GetMapping("pay/get/{id}")
    public Result getPayInfo(@PathVariable Integer id){
        return restTemplate.getForObject(PaymentSrv_URL+"/pay/get/"+id,Result.class,id);
    }
    @DeleteMapping("pay/del/{id}")
    public Result delOrder(@PathVariable Integer id){
        restTemplate.delete(PaymentSrv_URL+"/pay/del/"+id,id);
        return Result.ok(null);
    }
    @GetMapping("pay/getAll")
    public Result getAllOrder(){
        return restTemplate.getForObject(PaymentSrv_URL+"/pay/getAll",Result.class);
    }
    @PutMapping("pay/update")
    public Result updateOrder(@RequestBody PayDTO payDTO){
        restTemplate.put(PaymentSrv_URL+"/pay/update",payDTO);
        return Result.ok("成功修改记录，返回值");
    }
    @GetMapping("pay/get/info")
    public String getInfoByConsul(){
        return restTemplate.getForObject(PaymentSrv_URL+"/pay/get/info",String.class);
    }

    @Autowired
    private DiscoveryClient discoveryClient;
    @GetMapping("discovery")
    public String discovery()
    {
        List<String> services = discoveryClient.getServices();
        for (String element : services) {
            System.out.println(element);
        }

        System.out.println("===================================");

        List<ServiceInstance> instances = discoveryClient.getInstances("cloud-payment-service");
        for (ServiceInstance element : instances) {
            System.out.println(element.getServiceId()+"\t"+element.getHost()+"\t"+element.getPort()+"\t"+element.getUri());
        }

        return instances.get(0).getServiceId()+":"+instances.get(0).getPort();
    }

}
