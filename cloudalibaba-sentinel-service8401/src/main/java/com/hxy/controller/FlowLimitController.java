package com.hxy.controller;

import com.hxy.service.FlowLimitService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * Sentinel流量监控的控制类
 * 流控模式:
 * 1.直接：默认的流控模式，当接口到达限流条件时，直接开启限流功能
 * 2.关联：当关联的资源达到阈值，就限流自己
 * 3.链路：来自不同链路的请求对同一个目标访问时，实施针对性的不同限流措施
 * 流控效果：
 * 1.快速失败：直接失败，抛出异常
 * 2.Warmup(预热)：预热，先让流量进入，然后慢慢的放行
 * 3.排队等待：先排队，再放行
 */
@RestController
@Slf4j
public class FlowLimitController {

    @Autowired
    private FlowLimitService flowLimitService;

    @GetMapping("testA")
    public String testA() {
        return "----------testA";
    }

    @GetMapping("testB")
    public String testB() {
        return "----------testB";
    }

    /**
     * 流控-链路demo
     * C和D两个请求都访问flowLimitService.common()方法，达到阈值后对C限流，对D不管
     *
     * @return
     */
    @GetMapping("testC")
    public String testC() {
        flowLimitService.common();
        return "----------testC";
    }

    @GetMapping("testD")
    public String testD() {
        flowLimitService.common();
        return "----------testD";
    }

    /**
     * 流控效果的排队等待demo
     * @return
     */
    @GetMapping("testE")
    public String testE() {
        System.out.println(System.currentTimeMillis() + "      testE,排队等待");
        return "----------testE";
    }

    /**
     * 熔断规则-慢调用比例demo
     * @return
     */
    @GetMapping("testF")
    public String testF() {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("----测试：慢调用比例");
        return "----------testF 熔断规则-慢调用比例";
    }
    /**
     * 熔断规则-异常比例demo
     * @return
     */
    @GetMapping("/testG")
    public String testG(){
        int i = 10 / 0;
        return "--------testG,熔断规则，异常比例";
    }
    /**
     * 熔断规则-异常数demo
     * @return
     */
    @GetMapping("/testH")
    public String testH(){
        int i = 10 / 0;
        return "--------testH,熔断规则，异常数";
    }

}
