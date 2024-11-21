package com.hxy.apis;

import com.hxy.pojo.PayDTO;
import com.hxy.utils.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("cloud-payment-service")
public interface PayFeignApi {

    /**
     * 新增一条支付相关流水记录
     * @param payDTO
     * @return
     */
    @PostMapping("pay/add")
    public Result addPay(@RequestBody PayDTO payDTO);

    /**
     * 按照主键记录查询支付流水信息
     * @param id
     * @return
     */
    @GetMapping("pay/get/{id}")
    public Result getPayById(@PathVariable Integer id);

    /**
     * openfeign天然支持负载均衡演示
     * @return
     */
    @GetMapping("pay/get/info")
    public String mylb();

    /**
     *  Resilience4j CircuitBreaker 的例子
     * @param id
     * @return
     */
    @GetMapping("pay/circuit/{id}")
    public String myCircuit(@PathVariable Integer id);

    @GetMapping("pay/bulkhead/{id}")
    public String myBulkhead(@PathVariable Integer id);

    @GetMapping("pay/ratelimit/{id}")
    public String myRateLimit(@PathVariable Integer id);

    /**
     * Micrometer(Sleuth)进行链路监控的例子
     * @param id
     * @return
     */
    @GetMapping(value = "/pay/micrometer/{id}")
    public String myMicrometer(@PathVariable Integer id);

    /**
     * Gateway网关测试1
     * @param id
     * @return
     */
    @GetMapping("pay/gateway/get/{id}")
    public Result getById(@PathVariable Integer id);

    /**
     * Gateway网关测试2
     * @return
     */
    @GetMapping("pay/gateway/info")
    public String getGatewayInfo();
}
