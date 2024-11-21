package com.hxy.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hxy.apis.AccountFeignApi;
import com.hxy.apis.StorageFeignApi;
import com.hxy.pojo.Order;
import com.hxy.service.OrderService;
import com.hxy.mapper.OrderMapper;
import com.hxy.utils.Result;
import io.seata.core.context.RootContext;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author hxy
 * @description 针对表【t_order】的数据库操作Service实现
 * @createDate 2024-11-21 11:35:46
 */
@Service
@Slf4j
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order>
        implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

    // 下订单就要减少库存 通过openfeign去调用库存微服务
    @Autowired
    private StorageFeignApi storageFeignApi;
    // 下订单减少账户余额 通过openfeign去调用订单微服务
    @Autowired
    private AccountFeignApi accountFeignApi;

    @Override
    @GlobalTransactional(name = "hxy-create-order",rollbackFor = Exception.class)
    public void create(Order order) {
        // xid全局事务id的检查
        String xid = RootContext.getXID();
        // 1.新建订单
        log.info("------------------开始新建订单：" + "\t" + "xid：" + xid);
        // 订单新建时默认初始的订单状态为0
        order.setStatus(0);
        int rows = orderMapper.insert(order);
        // 插入订单成功后获得插入mysql的实体对象
        Order orderFromDB = null;
        if (rows > 0) {
            // 从mysql中查出刚插入的记录
            orderFromDB = orderMapper.selectById(order.getId());
            log.info("-------------> 新建订单成功,orderFromDB info：" + orderFromDB);
            System.out.println();
            // 扣减库存
            log.info("-------> 订单微服务开始调用Storage库存，做扣减count");
            storageFeignApi.decrease(orderFromDB.getProductId(),order.getCount());
            log.info("-------> 订单微服务结束调用Storage库存，做扣减完成");
            System.out.println();
            // 扣减账户余额
            log.info("-------> 订单微服务开始调用Account账号，做扣减money");
            accountFeignApi.decrease(orderFromDB.getUserId(),order.getMoney());
            log.info("-------> 订单微服务结束调用Account账号，做扣减完成");
            System.out.println();
            // 修改订单状态
            // 将订单状态从0修改为1
            log.info("-------> 修改订单状态");
            orderFromDB.setStatus(1);
            int updateResult = orderMapper.updateById(orderFromDB);
            log.info("-------> 修改订单状态完成"+"\t"+updateResult);


        }
        System.out.println();
        log.info("------------------结束新建订单：" + "\t" + "xid：" + xid);

    }


}




