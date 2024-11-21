package com.hxy.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("pay")
public class PayAlibabaController {

    @Value("${server.port}")
    private String serverPort;

    @GetMapping("nacos/{id}")
    public String getPayInfo(@PathVariable Integer id) {
        return "nacos registry, serverPort：" + serverPort + "\t id:" + id;
    }
}
