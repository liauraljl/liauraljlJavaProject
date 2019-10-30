package com.ljl.example.config;

import com.google.common.eventbus.EventBus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppBeanConfig {

    @Bean
    public EventBus eventBus() {
        return new EventBus("example");
    }
}
