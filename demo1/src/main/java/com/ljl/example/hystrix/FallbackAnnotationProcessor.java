package com.ljl.example.hystrix;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;

/**
 * @description: FallbackAnnotationProcessor
 */
@Component
public class FallbackAnnotationProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (AnnotationUtils.isAnnotationDeclaredLocally(Fallback.class, bean.getClass()) && FallbackService.class.isAssignableFrom(bean.getClass())) {
            FallbackRegistry.registerFallbackService((FallbackService) bean);
        }

        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }
}
