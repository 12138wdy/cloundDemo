package cn.itcast.gateway;


import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Order(-1)
@Component
public class AuthorizeFilter implements GlobalFilter {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        //1.获取请求参数
        ServerHttpRequest request = exchange.getRequest();
        MultiValueMap<String, String> queryParams = request.getQueryParams();
        //2.获取authorization
        String author = queryParams.getFirst("authorization");
        //3.校验
        if ("admin".equals(author)){
            //4.拦截
            //4.1放行
            return chain.filter(exchange);
        }
        //4.2设置状态码，拦截
        exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
        //结束处理
        return exchange.getResponse().setComplete();
    }
}
