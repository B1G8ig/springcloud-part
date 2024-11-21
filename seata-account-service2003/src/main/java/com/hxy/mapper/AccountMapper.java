package com.hxy.mapper;

import com.hxy.pojo.Account;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
* @author 大宝
* @description 针对表【t_account】的数据库操作Mapper
* @createDate 2024-11-21 14:37:08
* @Entity com.hxy.pojo.Account
*/
public interface AccountMapper extends BaseMapper<Account> {
    /**
     * @param userId
     * @param money 本次消费金额
     */
    void decrease(@Param("userId") Long userId, @Param("money") Long money);
}




