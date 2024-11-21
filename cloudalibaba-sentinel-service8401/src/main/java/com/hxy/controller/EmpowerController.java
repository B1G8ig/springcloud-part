package com.hxy.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 演示Sentinel授权的控制类
 */
@RestController
@Slf4j
public class EmpowerController {
    @GetMapping("/empower")
    public String testEmpower(){
        log.info("测试Sentinel授权规则empower");
        return "Sentinel授权规则";
    }

}
