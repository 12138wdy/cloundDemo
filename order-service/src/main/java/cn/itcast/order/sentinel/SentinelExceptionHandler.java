package cn.itcast.order.sentinel;

import com.alibaba.csp.sentinel.adapter.spring.webmvc.callback.BlockExceptionHandler;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.authority.AuthorityException;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeException;
import com.alibaba.csp.sentinel.slots.block.flow.FlowException;
import com.alibaba.csp.sentinel.slots.block.flow.param.ParamFlowException;
import org.springframework.stereotype.Component;
import org.yaml.snakeyaml.events.Event;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class SentinelExceptionHandler implements BlockExceptionHandler {
    @Override
    public void handle(HttpServletRequest httpServletRequest,
                       HttpServletResponse httpServletResponse,
                       BlockException e) throws Exception {
        String msg = "异常错误";
        int code = 429;

        if (e instanceof FlowException){
            msg = "请求被限流了";
        }else if (e instanceof ParamFlowException){
            msg = "请求被热点参数限流";
        }else if (e instanceof DegradeException){
            msg = "请求被降级了";
        }else if (e instanceof AuthorityException){
            msg = "没有权限访问";
            code = 401;
        }

        httpServletResponse.setContentType("application/json;charset=utf-8");
        httpServletResponse.setStatus(code);
        httpServletResponse.getWriter().println("{\"msg\": " + msg + ", \"status\": " + code + "}");

    }
}