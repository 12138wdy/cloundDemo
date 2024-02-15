package cn.itcast.fegin.clients;


import cn.itcast.fegin.pojo.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("userServer")
public interface UserClients {

    @GetMapping("/user/{id}")
    User findById(@PathVariable("id") Long id);


}
