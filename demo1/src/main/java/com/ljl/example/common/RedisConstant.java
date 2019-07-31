package com.ljl.example.common;

/**
 * Created by liaura_ljl on 2019/7/24.
 */
public class RedisConstant {

    /**
     * test hash
     */
    public static final String TEST_HASH="example:mapper-hash:%s";

    /**
     * Redis锁key
     */
    public static final String TEST_LOCK="example:mapper-lock:%s";

    /**
     * 流程信息key
     */
    public static final String PROCESS_KEY="example:process:pid:%s";
}
