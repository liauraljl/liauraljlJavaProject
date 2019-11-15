package com.ljl.example.service.componentTest;

import com.ljl.example.model.Process;

public interface Test3Service {
    void test3();

    /**
     * Redis连接测试
     */
    void test4();

    /**
     * 线程池测试
     */
    void test5();

    /**
     * Redis锁测试
     */
    void redisLockTest();

    /**
     * 数据库连接测试
     */
    void dbTest();

    /**
     * 缓存读取测试
     */
    Process readCache();

    /**
     * 更新操作 保持Redis和mysql强一致性
     * @return
     */
    Boolean updateProcessTest();
}
