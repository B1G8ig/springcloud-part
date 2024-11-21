package com.hxy.controller;

import com.hxy.pojo.Pay;
import com.hxy.pojo.PayDTO;
import com.hxy.service.PayService;
import com.hxy.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("pay")
@Slf4j
public class PayController {
    @Autowired
    private PayService payService;

    /**
     * 增加流水的业务的接口
     *
     * @return 返回增加数据的条数
     */
    @PostMapping("add")
    public Result<String> addPay(@RequestBody Pay pay) {
        int rows = payService.addPay(pay);
        return Result.ok("成功插入记录,返回值:" + rows);
    }

    /**
     * 删除流水的业务接口
     *
     * @return 返回删除数据的条数
     */
    @DeleteMapping("del/{id}")
    public Result<Integer> delPay(@PathVariable Integer id) {
        Integer rows = payService.delPay(id);
        return Result.ok(rows);
    }

    @PutMapping("update")
    public Result<String> updatePay(@RequestBody PayDTO payDTO) {
        Pay pay = new Pay();
        pay.setId(payDTO.getId());
        pay.setAmount(payDTO.getAmount());
        pay.setPayNo(payDTO.getPayNo());
        pay.setOrderNo(payDTO.getOrderNo());
        pay.setUserId(payDTO.getUserId());
        int rows = payService.updatePay(pay);
        return Result.ok("成功修改记录，返回值" + rows);
    }

    @GetMapping("get/{id}")
    public Result<Pay> getPayById(@PathVariable Integer id) {
        // 暂停62秒线程 测试feign默认调用超时时间
        // 测得feign默认超时时间为60秒
//        try {
//            TimeUnit.SECONDS.sleep(62);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
        Pay pay = payService.getPayById(id);
        return Result.ok(pay);
    }

    @GetMapping("getAll")
    public Result<List> getAll() {
        List<Pay> payList = payService.getAll();
        return Result.ok(payList);
    }

    @Value("${server.port}")
    private String port;

    // 从consul上拉取信息
    @GetMapping("get/info")
    public String getInfoByConsul(@Value("${hxyConsul.info}") String info) {
        return "hxyConsulInfo:" + info + "\t" + "port:" + port;
    }
}
