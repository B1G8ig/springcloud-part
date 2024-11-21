package com.hxy.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class RateLimitController {
    @GetMapping("rateLimit/byUrl")
    public String byUrl() {
        return "按Rest地址限流测试ok";
    }

    /**
     * 按SentinelResource资源名称限流+自定义限流返回
     *
     * @return
     */
    @GetMapping("rateLimit/byResource")
    @SentinelResource(value = "byResource", blockHandler = "handleBlockHandler")
    public String byResource() {
        return "按资源名称SentinelResource限流测试ok";
    }

    public String handleBlockHandler(BlockException blockException) {
        return "服务不可用触发了@SentinelResource启动，/(ㄒoㄒ)/~~";
    }

    /**
     * 按SentinelResource资源名称限流+自定义限流返回+服务降级
     *      blockHandler 主要针对sentinel配置后出现的违规情况处理
     *      fallback  程序异常了JVM抛出的异常服务降级
     */
    @GetMapping("rateLimit/doAction/{id}")
    @SentinelResource(value = "doActionSentinelResource", blockHandler = "doActionBlockHandler", fallback = "diActionFallback")
    public String doAction(@PathVariable Integer id) {
        if (id == 0) {
            throw new RuntimeException("id等于零直接异常");
        }
        return "doAction";
    }

    public String doActionBlockHandler(@PathVariable Integer id, BlockException e) {
        log.error("sentinel配置自定义限流了:{}" + e);
        return "sentinel配置自定义限流了";
    }

    public String diActionFallback(@PathVariable Integer id, Throwable t) {
        log.error("程序逻辑异常了");
        return "程序逻辑异常了" + t.getMessage();
    }

    /**
     * 热点参数限流
     *      设置p1超过阈值限流 p2放行
     *      参数例外项：当被限流的参数等于某个被设定的值时，改变对这个参数的限流阈值
     * @param p1
     * @param p2
     * @return
     */
    @GetMapping("/testHotKey")
    @SentinelResource(value = "testHotKey",blockHandler = "dealHandler_testHotKey")
    public String testHotKey(@RequestParam(value = "p1",required = false) String p1,

                             @RequestParam(value = "p2",required = false) String p2){
        return "------testHotKey";
    }
    public String dealHandler_testHotKey(String p1,String p2,BlockException exception)
    {
        return "-----dealHandler_testHotKey";
    }


}
