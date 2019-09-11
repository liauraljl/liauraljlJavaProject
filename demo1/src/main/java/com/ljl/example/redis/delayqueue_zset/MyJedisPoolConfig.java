package com.ljl.example.redis.delayqueue_zset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * Created by liaura_ljl on 2019/9/6.
 */
@Configuration
public class MyJedisPoolConfig {
    @Bean(name = "jedisPool")
    @Primary
    public JedisPool getJedisPool() {
        return new JedisPool(jedisPoolConfig(), host, port, 0, password, database);
    }

    public JedisPoolConfig jedisPoolConfig() {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(maxTotal);
        jedisPoolConfig.setMaxIdle(maxIdle);
        jedisPoolConfig.setNumTestsPerEvictionRun(numTestsPerEvictionRun);
        jedisPoolConfig.setTimeBetweenEvictionRunsMillis(5000);
        jedisPoolConfig.setMinEvictableIdleTimeMillis(10000);
        jedisPoolConfig.setSoftMinEvictableIdleTimeMillis(10000);
        jedisPoolConfig.setMaxWaitMillis(maxWaitMillis);
        jedisPoolConfig.setTestOnBorrow(testOnBorrow);
        jedisPoolConfig.setTestWhileIdle(true);
        jedisPoolConfig.setTestOnReturn(testOnReturn);
        jedisPoolConfig.setJmxEnabled(true);
        jedisPoolConfig.setJmxNamePrefix("");
        jedisPoolConfig.setBlockWhenExhausted(false);

        return jedisPoolConfig;
    }

    @Value("${redis.master.host:127.0.0.1}")
    private String host;
    @Value("${redis.master.port:6379}")
    private int port;
    @Value("${redis.master.password:123456}")
    private String password;
    @Value("${redis.master.database:0}")
    private int database;
    @Value("${redis.pool.maxActive:200}")
    private int maxTotal;
    @Value("${redis.pool.maxIdle:8}")
    private int maxIdle;
    @Value("${redis.pool.maxActive:200}")
    private int numTestsPerEvictionRun;
    @Value("${redis.pool.maxWait:3000}")
    private int maxWaitMillis;
    @Value("${redis.pool.testOnBorrow:true}")
    private boolean testOnBorrow;
    @Value("${redis.pool.testOnReturn:true}")
    private boolean testOnReturn;
}
