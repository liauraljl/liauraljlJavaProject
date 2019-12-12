package test1.threadTest.exceptionTest;

import com.alibaba.fastjson.JSON;

/**
 * Created by liaura_ljl on 2019/12/10.
 */
public class MyUncaughtExceptionHandler implements Thread.UncaughtExceptionHandler {
    @Override
    public void uncaughtException(Thread t, Throwable e) {
        System.err.println("子线程"+t.getName()+"的异常被我捕获：{}"+ JSON.toJSONString(e));
    }
}
