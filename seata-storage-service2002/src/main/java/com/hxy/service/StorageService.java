package com.hxy.service;

import com.hxy.pojo.Storage;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author 大宝
* @description 针对表【t_storage】的数据库操作Service
* @createDate 2024-11-21 14:22:26
*/
public interface StorageService extends IService<Storage> {
    /**
     * 扣减库存
     */
    void decrease(Long productId, Integer count);
}
