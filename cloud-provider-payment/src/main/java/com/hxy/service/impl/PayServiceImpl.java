package com.hxy.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hxy.pojo.Pay;
import com.hxy.service.PayService;
import com.hxy.mapper.PayMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.util.pattern.PathPattern;

import java.util.List;

/**
* @author 大宝
* @description 针对表【t_pay(支付交易表)】的数据库操作Service实现
* @createDate 2024-11-16 16:18:43
*/
@Service
public class PayServiceImpl extends ServiceImpl<PayMapper, Pay>
    implements PayService{

    @Autowired
    private PayMapper payMapper;
    @Override
    public int addPay(Pay pay) {
        int rows = payMapper.insert(pay);
        return rows;
    }

    @Override
    public Integer delPay(Integer id) {
        int rows = payMapper.deleteById(id);
        return rows;
    }

    @Override
    public int updatePay(Pay pay) {
        return payMapper.updateById(pay);
    }

    @Override
    public Pay getPayById(Integer id) {
        return payMapper.selectById(id);
    }

    @Override
    public List<Pay> getAll() {
        List<Pay> pays = payMapper.selectList(null);
        return pays;
    }
}




