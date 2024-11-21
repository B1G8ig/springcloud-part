package com.hxy.controller;

import cn.hutool.core.util.IdUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
public class PayCircuitController {

//    @GetMapping("pay/circuit/{id}")
//    public String myCircuit(@PathVariable Integer id) {
//        if (id == -4) {
//            throw new RuntimeException("id不能为负数");
//        }
//        if (id == 9999) {
//            try {
//                TimeUnit.SECONDS.sleep(5);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//        return "Hello ,circuit! inputId: " + id + "\t" + IdUtil.simpleUUID();
//    }

    // bulkhead的例子
    @GetMapping("pay/bulkhead/{id}")
    public String myBulkhead(@PathVariable Integer id) {
        if (id == -4) {
            throw new RuntimeException("id不能为负数");
        }
        if (id == 9999) {
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return "Hello ,bulkhead! inputId: " + id + "\t" + IdUtil.simpleUUID();
    }

    /**
     * 限流的例子
     * @param id
     * @return
     */
    @GetMapping("pay/ratelimit/{id}")
    public String myRateLimit(@PathVariable Integer id){
        return "Hello ,rateLimit! inputId: " + id + "\t" + IdUtil.simpleUUID();
    }
}
