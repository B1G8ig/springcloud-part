package com.hxy.config;
import feign.Logger;
import feign.Retryer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfig {
    @Bean
    public Retryer myRetryer() {

        return Retryer.NEVER_RETRY;
        // 最大请求次数为3 初始间隔时间为100ms 重试最大间隔时间为1s
        //return new Retryer.Default(100, 1, 3);
    }
    @Bean
    Logger.Level feignLoggerLevel(){
        return Logger.Level.FULL;
    }
}
