package com.hxy.apis;

import com.hxy.utils.Result;
import com.hxy.utils.ResultCodeEnum;
import org.springframework.stereotype.Component;

@Component
public class PayFeignSentinelApiFallback implements PayFeignSentinelApi{
    @Override
    public Result getPayOrderNo(String orderNo) {
        return Result.build(null, ResultCodeEnum.RC500.getCode(),"对方服务宕机或不可用，FallBack服务降级/(ㄒoㄒ)/~~");
    }
}
