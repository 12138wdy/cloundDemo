package cn.itcast.fegin.config;


import cn.itcast.fegin.clients.Fallback.userClientFallbackFactory;
import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;

public class DefaultFeignConfiguration {

    @Bean
    public Logger.Level level ()  {
        return Logger.Level.BASIC;
    }

    @Bean
    public userClientFallbackFactory userClientFallbackFactory(){
        return new userClientFallbackFactory();
    }
}
