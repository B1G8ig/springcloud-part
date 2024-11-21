package com.hxy.apis;

import com.hxy.utils.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 账户的服务接口
 */
@FeignClient("seata-account-service")
public interface AccountFeignApi {
    // 扣减账户余额
    @PostMapping("account/decrease")
    Result decrease(@RequestParam Long userId, @RequestParam Long money);
}
