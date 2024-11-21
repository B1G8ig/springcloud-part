package com.hxy.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hxy.pojo.Account;
import com.hxy.service.AccountService;
import com.hxy.mapper.AccountMapper;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
* @author 大宝
* @description 针对表【t_account】的数据库操作Service实现
* @createDate 2024-11-21 14:37:08
*/
@Service
@Slf4j
public class AccountServiceImpl extends ServiceImpl<AccountMapper, Account>
    implements AccountService{
    @Resource
    AccountMapper accountMapper;

    /**
     * 扣减账户余额
     */
    @Override
    public void decrease(Long userId, Long money) {
        log.info("------->account-service中扣减账户余额开始");

        accountMapper.decrease(userId,money);

        //myTimeOut();
        //int age = 10/0;
        log.info("------->account-service中扣减账户余额结束");
    }

    /**
     * 模拟超时异常，全局事务回滚
     */
    private static void myTimeOut()
    {
        try { TimeUnit.SECONDS.sleep(65); } catch (InterruptedException e) { e.printStackTrace(); }
    }
}




