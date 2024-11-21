package com.hxy.service;

import com.hxy.pojo.Order;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author hxy
* @description 针对表【t_order】的数据库操作Service
* @createDate 2024-11-21 11:35:46
*/
public interface OrderService extends IService<Order> {

    void create(Order order);
}
