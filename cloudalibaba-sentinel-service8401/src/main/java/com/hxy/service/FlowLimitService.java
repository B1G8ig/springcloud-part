package com.hxy.service;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import org.springframework.stereotype.Service;

@Service
public class FlowLimitService {

    @SentinelResource("common")
    public void common(){
        System.out.println("---------FlowLimitService come in");
    }
}
