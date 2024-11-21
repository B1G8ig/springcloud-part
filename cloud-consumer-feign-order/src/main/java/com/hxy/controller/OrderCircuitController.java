package com.hxy.controller;

import com.hxy.apis.PayFeignApi;
import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import org.apache.hc.core5.concurrent.CompletedFuture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

@RestController
public class OrderCircuitController {
    @Autowired
    private PayFeignApi payFeignApi;

    @GetMapping("feign/pay/circuit/{id}")
    @CircuitBreaker(name = "cloud-payment-service", fallbackMethod = "myCircuitFallback")
    public String myCircuit(@PathVariable Integer id) {
        return payFeignApi.myCircuit(id);
    }

    /**
     * 服务降级后的兜底处理方法
     *
     * @param t
     * @return
     */
    public String myCircuitFallback(Integer id, Throwable t) {
        return "myCircuitFallback,系统繁忙,请稍后再试------/(ㄒoㄒ)/~~";
    }

    /**
     * 舱壁隔离 - 信号量
     *
     * @param id
     * @return
     */
//    @GetMapping("feign/pay/bulkhead/{id}")
//    @Bulkhead(name = "cloud-payment-service",fallbackMethod = "myBulkheadFallback",type = Bulkhead.Type.SEMAPHORE)
//    public String myBulkhead(@PathVariable Integer id) {
//        return payFeignApi.myBulkhead(id);
//    }


    /**
     * 舱壁隔离 - 线程池
     *
     * @param id
     * @return
     */
    @GetMapping("feign/pay/bulkhead/{id}")
    @Bulkhead(name = "cloud-payment-service", fallbackMethod = "myBulkheadPoolFallback", type = Bulkhead.Type.THREADPOOL)
    public CompletableFuture<String> myBulkheadTHREADPOOL(@PathVariable Integer id) {
        System.out.println(Thread.currentThread().getName() + "\t" + "---开始进入");
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(Thread.currentThread().getName() + "\t" + "---准备离开");
        return CompletableFuture.supplyAsync(() -> payFeignApi.myBulkhead(id) + "\t" + "Bulkhead.Type.THREADPOOL");
    }

    public CompletableFuture<String> myBulkheadPoolFallback(Integer id, Throwable t) {
        return CompletableFuture.supplyAsync(() -> "Bulkhead.Type.THREADPOOL,系统繁忙,请稍后再试------/(ㄒoㄒ)/~~");
    }

    @GetMapping("feign/pay/ratelimit/{id}")
    @RateLimiter(name = "cloud-payment-service", fallbackMethod = "myRateLimitFallback")
    public String myRateLimit(@PathVariable Integer id) {
        return payFeignApi.myRateLimit(id);
    }

    public String myRateLimitFallback(Integer id,Throwable t){
        return "你被限流了，禁止访问/(ㄒoㄒ)/~~";
    }
}
