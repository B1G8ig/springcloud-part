package com.hxy.service;

import com.hxy.pojo.Account;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

/**
* @author 大宝
* @description 针对表【t_account】的数据库操作Service
* @createDate 2024-11-21 14:37:08
*/
public interface AccountService extends IService<Account> {
    /**
     * 扣减账户余额
     * @param userId 用户id
     * @param money 本次消费金额
     */
    void decrease(@Param("userId") Long userId, @Param("money") Long money);
}
