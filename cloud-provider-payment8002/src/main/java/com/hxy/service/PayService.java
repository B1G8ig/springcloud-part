package com.hxy.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hxy.pojo.Pay;

import java.util.List;

/**
* @author 大宝
* @description 针对表【t_pay(支付交易表)】的数据库操作Service
* @createDate 2024-11-16 16:18:43
*/
public interface PayService extends IService<Pay> {

    int addPay(Pay pay);

    Integer delPay(Integer id);

    int updatePay(Pay pay);

    Pay getPayById(Integer id);

    List<Pay> getAll();
}
