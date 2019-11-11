package com.ljl.example.hystrix;

import java.lang.annotation.*;

/**
 * 服务降级实体类注解标志
 */
@Documented
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Fallback {

    /**
     * 服务降级接口
     */
    Class interfaceClass();

    /**
     * 服务降级接口别名
     */
    String interfaceAlias() default "";

    /**
     * 服务降级接口下的某个方法
     */
    String methodName();

    /**
     * 服务降级接口下的某个方法别名
     */
    String methodNameAlias() default "";

    /**
     * 方法中参数个数
     */
    int argsSize() default -1;
}
