package com.hxy.apis;

import com.hxy.utils.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "nacos-payment-provider",fallback = PayFeignSentinelApiFallback.class)
public interface PayFeignSentinelApi {

    @GetMapping("pay/nacos/get/{orderNo}")
    public Result getPayOrderNo(@PathVariable String orderNo);
}
