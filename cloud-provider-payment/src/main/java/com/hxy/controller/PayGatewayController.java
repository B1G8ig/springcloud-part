package com.hxy.controller;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import com.hxy.pojo.Pay;
import com.hxy.service.PayService;
import com.hxy.utils.Result;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Enumeration;

@RestController
@RequestMapping("pay")
public class PayGatewayController {
    @Autowired
    private PayService payService;

    @GetMapping("gateway/get/{id}")
    public Result<Pay> getById(@PathVariable Integer id) {
        Pay pay = payService.getById(id);
        return Result.ok(pay);
    }

    @GetMapping("gateway/info")
    public Result<String> getGatewayInfo() {
        return Result.ok("gateway info tset：" + IdUtil.simpleUUID());
    }

    @GetMapping(value = "gateway/filter")
    public Result<String> getGatewayFilter(HttpServletRequest request) {
        String result = "";
        Enumeration<String> headers = request.getHeaderNames();
        while (headers.hasMoreElements()) {
            String headName = headers.nextElement();
            String headValue = request.getHeader(headName);
            System.out.println("请求头名: " + headName + "\t\t\t" + "请求头值: " + headValue);
            if (headName.equalsIgnoreCase("X-Request-hxy1")
                    || headName.equalsIgnoreCase("X-Request-hxy2")) {
                result = result + headName + "\t " + headValue + " ";
            }
        }
        System.out.println("===========================================================");
        String customerId = request.getParameter("customerId");
        System.out.println("request Parameter customerId = " + customerId);
        String customerName = request.getParameter("customerName");
        System.out.println("request Parameter customerName = " + customerName);
        System.out.println("===========================================================");
        return Result.ok("getGatewayFilter 过滤器 test： " + result + " \t " + DateUtil.now());
    }

}
