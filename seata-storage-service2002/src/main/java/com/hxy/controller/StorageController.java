package com.hxy.controller;

import com.hxy.service.StorageService;
import com.hxy.utils.Result;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StorageController {
    @Resource
    private StorageService storageService;

    /**
     * 扣减库存
     */
    @RequestMapping("/storage/decrease")
    public Result decrease(Long productId, Integer count) {

        storageService.decrease(productId, count);
        return Result.ok("扣减库存成功!");
    }
}
