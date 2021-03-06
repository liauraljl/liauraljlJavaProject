package com.ljl.example.redis;

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

    /***************kafka 消息 **************************/

    /**
     *kafka消息key
     */
    public static final String KAFKA_MSG_KEY="demo1:kafka:msg:%s";

    /**
     * piepline 批量set key
     */
    public static final String TEST_PIEPLINE_BATCHSET="example:piepline:string:process:%s";

    /**
     * hyperloglog 测试  key
     */
    public static final String TEST_HYPERLOGLOG_KEY1="example:test:hyperloglog:%s";

    /**
     * bit 测试  key
     */
    public static final String TEST_BIT_KEY1="example:test:bit:%s";

    /**
     * zset 测试 key
     */
    public static final String TEST_ZSET_KEY1="example:test:zset:%s";
}
