package com.hxy.mapper;

import com.hxy.pojo.Storage;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
* @author 大宝
* @description 针对表【t_storage】的数据库操作Mapper
* @createDate 2024-11-21 14:22:26
* @Entity com.hxy.pojo.Storage
*/
public interface StorageMapper extends BaseMapper<Storage> {
    /**
     * 扣减库存
     */
    void decrease(@Param("productId") Long productId, @Param("count") Integer count);
}




