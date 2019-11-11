package com.ljl.example.hystrix;

import com.netflix.hystrix.HystrixCommandProperties;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * @description: HystrixConfig
 */
@Data
@Primary
@Configuration
@ConfigurationProperties(prefix = "hystrix")
public class HystrixConfig {
    /**
     * circuit breaker
     */
    private Integer circuitBreakerRequestVolumeThreshold;
    private Integer circuitBreakerSleepWindowInMilliseconds;
    private Integer circuitBreakerErrorThresholdPercentage;

    /**
     * execution
     */
    private String executionIsolationStrategy;
    private HystrixCommandProperties.ExecutionIsolationStrategy isolationStrategy;
    private Integer executionTimeoutInMilliseconds;
    private Integer executionIsolationSemaphoreMaxConcurrentRequests;

    /**
     * fallback
     */
    private Boolean fallbackEnabled;
    private Integer fallbackIsolationSemaphoreMaxConcurrentRequests;

    /**
     * metrics
     */
    private Integer metricsRollingStatisticalWindow;
    private Integer metricsRollingStatisticalWindowBuckets;

    /**
     * thread pool
     */
    private Integer corePoolSize;
    private Integer maximumPoolSize;
    private Integer maxQueueSize;
    private Integer queueSizeRejectionThreshold;
    private Integer keepAliveTime;
    private Boolean allowMaximumSizeToDivergeFromCoreSize;

    private Boolean isOpen;
    private String onlyFor;

    public HystrixCommandProperties.ExecutionIsolationStrategy getIsolationStrategy() {
        switch (executionIsolationStrategy) {
            case "SEMAPHORE":
                isolationStrategy = HystrixCommandProperties.ExecutionIsolationStrategy.SEMAPHORE;
                break;
            case "THREAD":
                isolationStrategy = HystrixCommandProperties.ExecutionIsolationStrategy.THREAD;
                break;
            default:
                isolationStrategy = HystrixCommandProperties.ExecutionIsolationStrategy.SEMAPHORE;
                break;
        }

        return isolationStrategy;
    }

}
