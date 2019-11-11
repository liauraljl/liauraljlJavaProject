package com.ljl.example.hystrix.fallback;

import com.alibaba.dubbo.rpc.Invocation;
import com.alibaba.dubbo.rpc.Invoker;
import com.ljl.example.common.SoaResponse;
import com.ljl.example.hystrix.Fallback;
import com.ljl.example.hystrix.FallbackService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @description: DefaultFallbackService
 */
@Fallback(interfaceClass = FallbackService.class, interfaceAlias = "default",
          methodName = "fallback", methodNameAlias = "default")
@Slf4j
@Component
public class DefaultFallbackService implements FallbackService {

    @Override
    public SoaResponse fallback(Invoker invoker, Invocation invocation) {
        log.info("default fallback service is invoked...");
        SoaResponse<Void, ?> response = new SoaResponse<>();
        response.setReturnCode("000000");
        response.setReturnMsg("请求的服务压力过大，暂不可达...");
        return response;
    }

    @Override
    public String fallBackStr(Invoker invoker, Invocation invocation) {
        return "ss";
    }
}
