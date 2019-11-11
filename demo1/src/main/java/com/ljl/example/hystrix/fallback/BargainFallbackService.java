package com.ljl.example.hystrix.fallback;

import com.alibaba.dubbo.rpc.Invocation;
import com.alibaba.dubbo.rpc.Invoker;
import com.ljl.example.common.SoaResponse;
import com.ljl.example.hystrix.Fallback;
import com.ljl.example.hystrix.FallbackService;
import com.ljl.example.service.other.Test2Service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @description: BargainFallbackService
 */
@Fallback(interfaceClass = Test2Service.class, interfaceAlias = "test2Service",
          methodName = "test2", methodNameAlias = "test2")
@Slf4j
@Component
public class BargainFallbackService implements FallbackService {

    @Override
    public SoaResponse fallback(Invoker invoker, Invocation invocation) {
        log.info("test2Service service is fallback...");
        SoaResponse<Void, ?> response = new SoaResponse<>();
        response.setReturnCode(String.valueOf(31000000019L));
        response.setReturnMsg("请求过于频繁，稍后再试~");
        return response;
    }

    @Override
    public String fallBackStr(Invoker invoker, Invocation invocation) {
        return "ss";
    }
}
