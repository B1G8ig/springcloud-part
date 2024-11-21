package com.hxy.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hxy.pojo.Storage;
import com.hxy.service.StorageService;
import com.hxy.mapper.StorageMapper;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
* @author 大宝
* @description 针对表【t_storage】的数据库操作Service实现
* @createDate 2024-11-21 14:22:26
*/
@Service
@Slf4j
public class StorageServiceImpl extends ServiceImpl<StorageMapper, Storage>
    implements StorageService{

    @Resource
    private StorageMapper storageMapper;

    /**
     * 扣减库存
     */
    @Override
    public void decrease(Long productId, Integer count) {
        log.info("------->storage-service中扣减库存开始");
        storageMapper.decrease(productId,count);
        log.info("------->storage-service中扣减库存结束");
    }

}




