package com.ljl.example.config.spring.configForClassOrPackage;


import com.ljl.example.config.spring.MyAnnotationBeanNameGenerator;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "com.ljl.example.service.componentTest",nameGenerator = MyAnnotationBeanNameGenerator.class)
public class Test3ServiceConfig {
}
