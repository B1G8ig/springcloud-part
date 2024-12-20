package com.hxy.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
public class NacosConfigClientController {

    @Value("${config.info}")
    private String configInfo;

    @GetMapping("config/info")
    public String getConfigInfo() {
        return configInfo;
    }
}
