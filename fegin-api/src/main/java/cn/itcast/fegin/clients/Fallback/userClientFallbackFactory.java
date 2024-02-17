package cn.itcast.fegin.clients.Fallback;

import cn.itcast.fegin.clients.UserClients;
import cn.itcast.fegin.pojo.User;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class userClientFallbackFactory implements FallbackFactory<UserClients> {


    @Override
    public UserClients create(Throwable throwable) {
        return new UserClients() {
            @Override
            public User findById(Long id) {
                log.error("服务异常",throwable);
                return new User();
            }
        };
    }
}
