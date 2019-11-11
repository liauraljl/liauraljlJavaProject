package com.ljl.example.hystrix;

import com.alibaba.dubbo.rpc.Invocation;
import com.alibaba.dubbo.rpc.Invoker;
import com.ljl.example.common.SoaResponse;

/**
 * @description: FallbackService
 */
public interface FallbackService {

    SoaResponse fallback(Invoker invoker, Invocation invocation);

    String fallBackStr(Invoker invoker, Invocation invocation);
}
