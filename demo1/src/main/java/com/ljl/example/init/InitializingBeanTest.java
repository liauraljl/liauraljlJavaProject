package com.ljl.example.init;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

@Component
public class InitializingBeanTest implements InitializingBean {
    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("启初始化:InitializingBean");
    }
}
