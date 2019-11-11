package com.ljl.example.hystrix;

import com.alibaba.dubbo.rpc.*;
import com.ljl.example.common.SoaResponse;
import com.netflix.hystrix.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.ClassUtils;

/**
 * @description: DubboHystrixCommand
 */
@Slf4j
public class DubboHystrixCommand extends HystrixCommand<String> {

    private Invoker invoker;

    private Invocation invocation;

    public DubboHystrixCommand(Invoker invoker, Invocation invocation, HystrixConfig hystrixConfig) {
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey(ClassUtils.getQualifiedName(invoker.getInterface())))
                    .andCommandKey(HystrixCommandKey.Factory.asKey(String.format("%s.%s", invocation.getMethodName(),
                                   invocation.getArguments() == null ? 0 : invocation.getArguments().length)))
                    .andCommandPropertiesDefaults(
                            HystrixCommandProperties.Setter()
                                    .withCircuitBreakerRequestVolumeThreshold(hystrixConfig.getCircuitBreakerRequestVolumeThreshold())
                                    .withCircuitBreakerErrorThresholdPercentage(hystrixConfig.getCircuitBreakerErrorThresholdPercentage())
                                    .withCircuitBreakerSleepWindowInMilliseconds(hystrixConfig.getCircuitBreakerSleepWindowInMilliseconds())
                                    .withExecutionIsolationStrategy(hystrixConfig.getIsolationStrategy())
                                    .withExecutionIsolationSemaphoreMaxConcurrentRequests(hystrixConfig.getExecutionIsolationSemaphoreMaxConcurrentRequests())
                                    .withExecutionTimeoutInMilliseconds(hystrixConfig.getExecutionTimeoutInMilliseconds())
                                    .withFallbackEnabled(hystrixConfig.getFallbackEnabled())
                                    .withFallbackIsolationSemaphoreMaxConcurrentRequests(hystrixConfig.getFallbackIsolationSemaphoreMaxConcurrentRequests())
                                    .withMetricsRollingStatisticalWindowBuckets(hystrixConfig.getMetricsRollingStatisticalWindowBuckets())
                                    .withMetricsRollingStatisticalWindowInMilliseconds(hystrixConfig.getCircuitBreakerSleepWindowInMilliseconds())
                    )
                    .andThreadPoolPropertiesDefaults(
                            HystrixThreadPoolProperties.Setter()
                                    .withCoreSize(hystrixConfig.getCorePoolSize())
                                    .withMaximumSize(hystrixConfig.getMaximumPoolSize())
                                    .withMaxQueueSize(hystrixConfig.getMaxQueueSize())
                                    .withKeepAliveTimeMinutes(hystrixConfig.getKeepAliveTime())
                                    .withQueueSizeRejectionThreshold(hystrixConfig.getQueueSizeRejectionThreshold())
                                    .withAllowMaximumSizeToDivergeFromCoreSize(hystrixConfig.getAllowMaximumSizeToDivergeFromCoreSize())
                    )
                );

        this.invoker = invoker;
        this.invocation = invocation;
    }

    @Override
    protected String run() throws Exception {
        log.info("dubbo service is invoked by hysrix...");
        Result result = invoker.invoke(invocation);
        if (result.hasException()) {
            log.error("hysrix run has exceptin: {}", result.getException());
            throw new RpcException(result.getException());
        }

        return result.getValue().toString();
    }

    @Override
    protected String getFallback() {
        log.info("invoke fallback service: {}.{}", getInterfaceName(invoker), getMethodName(invocation));
        FallbackService fallbackService = FallbackRegistry.getFallbackService(invoker, invocation);
        if (fallbackService == null) {
            log.info("hystrix can not find method: {} in class: {}", invocation.getMethodName(), invoker.getInterface());
            fallbackService = FallbackRegistry.getDefaultFallbackService();

        }
        String result = fallbackService.fallBackStr(invoker, invocation);

        return result;
    }

    private String getInterfaceName(Invoker invoker){
        return ClassUtils.getQualifiedName(invoker.getInterface());
    }

    private String getMethodName(Invocation invocation){
        return invocation.getMethodName();
    }
}
