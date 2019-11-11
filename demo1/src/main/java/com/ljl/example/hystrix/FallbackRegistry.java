package com.ljl.example.hystrix;

import com.alibaba.dubbo.rpc.Invocation;
import com.alibaba.dubbo.rpc.Invoker;
import com.ljl.example.hystrix.fallback.DefaultFallbackService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.util.ClassUtils;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @description: FallbackRegistry
 */
@Slf4j
public class FallbackRegistry {

    /**
     * Struct
     *  key: qualifiedInterfaceName.methodName.argsSize
     *  value: Fallback Service
     */
    private static final Map<String, FallbackService> services = new ConcurrentHashMap();

    /**
     * Struct
     *  key: interfaceAlias.methodNameAlias
     *  value: qualifiedInterfaceName.methodName.argsSize
     */
    private static final Map<String, String> alias = new ConcurrentHashMap<>();

    private static final String INTERFACE_KEY_FORMAT = "%s.%s.%s";

    private static final String ALIAS_KEY_FORMAT = "%s.%s";

    public static void registerFallbackService(FallbackService fallbackService){
        if (fallbackService == null) {
            return;
        }
        String serviceKey = getServiceKey(fallbackService);
        String aliasKey = getAliasKey(fallbackService);
        services.put(serviceKey, fallbackService);
        alias.put(aliasKey, serviceKey);
    }

    public static FallbackService getDefaultFallbackService(){
        return new DefaultFallbackService();
    }

    public static FallbackService getFallbackService(Invoker invoker, Invocation invocation){
        String serviceKey = getServiceKey(invoker, invocation);
        return services.get(serviceKey);
    }

    public static String getServiceKey(Invoker invoker, Invocation invocation){
        int argsSize = invocation.getArguments() == null ? 0 : invocation.getArguments().length;
        return String.format(INTERFACE_KEY_FORMAT, getQualifiedInterfaceName(invoker.getInterface()), invocation.getMethodName(), argsSize);
    }

    public static String getServiceKey(FallbackService fallbackService){
        Fallback fallback = AnnotationUtils.getAnnotation(fallbackService.getClass(), Fallback.class);
        return String.format(INTERFACE_KEY_FORMAT, getQualifiedInterfaceName(fallback.interfaceClass()), fallback.methodName(), getArgsSize(fallback));
    }

    public static String getAliasKey(FallbackService fallbackService) {
        Fallback fallback = AnnotationUtils.getAnnotation(fallbackService.getClass(), Fallback.class);
        String interfaceAlias = getInterfaceAlias(fallback);
        String methodNameAlias = getMethodAlias(fallback);
        return String.format(ALIAS_KEY_FORMAT, interfaceAlias, methodNameAlias);
    }

    private static String getQualifiedInterfaceName(Class<?> clazz){
        return ClassUtils.getQualifiedName(clazz);
    }

    private static int getArgsSize(Fallback fallback) {
        if (fallback.argsSize() < -1) {
            throw new IllegalArgumentException("arguments length must be positive");
        }
        if (fallback.argsSize() != -1) {
            return fallback.argsSize();
        }
        for (Method method : fallback.interfaceClass().getDeclaredMethods()) {
            if (StringUtils.isNotEmpty(fallback.methodName()) && fallback.methodName().equals(method.getName())) {
                return method.getParameterTypes().length;
            }
        }
        throw new RuntimeException(
                String.format("method: %s can't be found in class: %s", fallback.methodName(), getQualifiedInterfaceName(fallback.interfaceClass()))
        );
    }

    private static String getInterfaceAlias(Fallback fallback) {
        return StringUtils.isEmpty(fallback.interfaceAlias()) ? getQualifiedInterfaceName(fallback.interfaceClass()) : fallback.interfaceAlias();
    }

    private static String getMethodAlias(Fallback fallback) {
        return StringUtils.isEmpty(fallback.methodNameAlias()) ? fallback.methodName() : fallback.interfaceAlias();
    }

}
