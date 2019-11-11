package com.ljl.example.hystrix;

import com.alibaba.dubbo.common.Constants;
import com.alibaba.dubbo.common.extension.Activate;
import com.alibaba.dubbo.rpc.*;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

///**
// * @description: HystrixFilter
// */
//@Slf4j
//@Data
//@Activate(group = Constants.CONSUMER)
//public class HystrixFilter implements Filter {
//
//    private HystrixConfig hystrixConfig;
//
//    @Override
//    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
//        if (!hystrixConfig.getIsOpen()) {
//            log.info("hystrix is not open, direct call dubbo service...");
//            return invoker.invoke(invocation);
//        }
//        if (hystrixConfig.getOnlyFor().contains(FallbackRegistry.getServiceKey(invoker, invocation))
//                || FallbackRegistry.getFallbackService(invoker, invocation) != null) {
//            DubboHystrixCommand command = new DubboHystrixCommand(invoker, invocation, hystrixConfig);
//            return command.execute();
//        }
//        return invoker.invoke(invocation);
//    }
//
//}
