package com.hxy.controller;

import com.hxy.pojo.Order;
import com.hxy.service.OrderService;
import com.hxy.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("order")
public class OrderController {
    @Autowired
    private OrderService orderService;
    @PostMapping("create")
    public Result create(@RequestBody Order order){
        orderService.create(order);
        return Result.ok(order);
    }
}
