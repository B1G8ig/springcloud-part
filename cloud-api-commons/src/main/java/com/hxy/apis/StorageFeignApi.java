package com.hxy.apis;

import com.hxy.utils.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 库存的服务接口
 */
@FeignClient("seata-storage-service")
public interface StorageFeignApi {
    /**
     * 扣减库存
     * @param productId
     * @param count
     * @return
     */
    @PostMapping("storage/decrease")
    Result decrease(@RequestParam Long productId, @RequestParam Integer count);
}
