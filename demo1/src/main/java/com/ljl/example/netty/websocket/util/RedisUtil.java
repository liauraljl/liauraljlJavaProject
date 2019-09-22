package com.ljl.example.netty.websocket.util;

import com.google.common.collect.Sets;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import redis.clients.jedis.JedisCommands;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component
public class RedisUtil {
    private static final long DEFAULT_EXPIRE_TIME = 10;

    @Getter
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Getter
    @Autowired
    private RedisTemplate redisTemplate;

    public Set<Long> getLongSet(String key) {
        Set<String> members = stringRedisTemplate.opsForSet().members(key);
        if (CollectionUtils.isEmpty(members)) {
            return Collections.emptySet();
        }
        Set<Long> longSet = Sets.newHashSetWithExpectedSize(members.size());
        for (String mem : members) {
            longSet.add(Long.valueOf(mem));
        }
        return longSet;
    }

    public boolean isMember(String key, Object obj) {
        return stringRedisTemplate.opsForSet().isMember(key, obj);
    }

    public void setInt(String key, int value) {
        stringRedisTemplate.opsForValue().set(key, String.valueOf(value));
    }

    public void setLong(String key, long value) {
        stringRedisTemplate.opsForValue().set(key, String.valueOf(value));
    }

    public Integer getInt(String key) {
        String value = stringRedisTemplate.opsForValue().get(key);
        if (value == null) {
            return null;
        }
        return Integer.valueOf(value);
    }

    public Long getLong(String key) {
        String s = stringRedisTemplate.opsForValue().get(key);
        return s == null ? null : Long.valueOf(s);
    }

    public long getLongWithDefault(String key, long defaultValue) {
        Long longValue = getLong(key);
        if (longValue == null) {
            return defaultValue;
        }
        return longValue;
    }

    public long increment(String key, long delta) {
        return stringRedisTemplate.opsForValue().increment(key, delta);
    }

    public void resetLockTime(String lock) {
        stringRedisTemplate.expire(lock, DEFAULT_EXPIRE_TIME, TimeUnit.SECONDS);
    }

    public void setExpireTime(String key, long timeout, TimeUnit timeUnit) {
        stringRedisTemplate.expire(key, timeout, timeUnit);
    }

    public Long listRightPushAll(String key, Collection values) {
        return redisTemplate.opsForList().rightPushAll(key, values);
    }

    public Long listRightPush(String key, Object values) {
        return redisTemplate.opsForList().rightPush(key, values);
    }

    public Long listSize(String key) {
        return redisTemplate.opsForList().size(key);
    }

    public void listSet(String key, int index, Collection values) {
        redisTemplate.opsForList().set(key, 0, values);
    }

    public void listTrim(String key, long start, long end) {
        redisTemplate.opsForList().trim(key, start, end);
    }

    public <T> List<T> listRange(String key, long start, long end) {
        return redisTemplate.opsForList().range(key, start, end);
    }

    public <T> T listLpop(String key, Class<T> cls) {
        return cls.cast(redisTemplate.opsForList().leftPop(key));
    }

    public boolean setIfAbsent(String key, Object value) {
        return stringRedisTemplate.opsForValue().setIfAbsent(key, value.toString());
    }

    public String getAndSet(String key, String value) {
        String s = stringRedisTemplate.opsForValue().getAndSet(key, value);
        return s;
    }

    public String getString(String key) {
        String s = stringRedisTemplate.opsForValue().get(key);
        return s;
    }

    public void setString(String key, String value) {
        stringRedisTemplate.opsForValue().set(key, value);
    }

    /**
     * 包含过期时间
     *
     * @param key
     * @param value
     * @param time
     */
    public void setString(String key, String value, Long time, TimeUnit timeUnit) {
        stringRedisTemplate.opsForValue().set(key, value, time, timeUnit);
    }

    public void addValueInSet(String redisFivHadecheckFaceOrdernoSetKey, String... orderno) {
        stringRedisTemplate.opsForSet().add(redisFivHadecheckFaceOrdernoSetKey, orderno);
    }


    private static final String LOCK_SUCCESS = "OK";
    private static final String SET_IF_NOT_EXIST = "NX";
    //毫秒
    private static final String SET_WITH_EXPIRE_TIME = "PX";

    /**
     * 获取分布式锁
     *
     * @param lockKey
     * @param requestId   加锁方id
     * @param millisecond
     * @return
     */

    public boolean lock(final String lockKey, final String requestId, final Long millisecond) {
        RedisCallback<String> callback = new RedisCallback<String>() {
            @Override
            public String doInRedis(RedisConnection connection) throws DataAccessException {
                JedisCommands commands = (JedisCommands) connection.getNativeConnection();
                return commands.set(lockKey, requestId, SET_IF_NOT_EXIST, SET_WITH_EXPIRE_TIME, millisecond);
            }
        };
        String result = stringRedisTemplate.execute(callback);
        if (null == result) {
            return false;
        } else if (LOCK_SUCCESS.equals(result)) {
            return true;
        }
        return false;
    }

    /**
     * 解锁
     *
     * @param lockKey
     * @param requestId 解锁方id
     * @return
     */
    public boolean releaselock(String lockKey, String requestId) {
        if (requestId.equals(stringRedisTemplate.opsForValue().get(lockKey))) {
            //这一步存在并发问题，但是公司不支持执行lua脚本，所以只能这样了
            stringRedisTemplate.delete(lockKey);
            return true;
        } else {
            return false;
        }
    }

    /*public void executeWithLock(String lock, RunExecutor executor) {
        if (stringRedisTemplate.opsForValue().setIfAbsent(lock, Thread.currentThread().getName())) {
            try {
                stringRedisTemplate.expire(lock, DEFAULT_EXPIRE_TIME, TimeUnit.SECONDS);
                executor.execute();
            } finally {
                stringRedisTemplate.expire(lock, 1, TimeUnit.SECONDS);
            }
        } else {
            Long expire = stringRedisTemplate.getExpire(lock);
            if (expire == -1) {
                stringRedisTemplate.expire(lock, DEFAULT_EXPIRE_TIME, TimeUnit.SECONDS);
            }
        }
    }*/

    public Long listRemoveValue(String key, Object values) {
        //key，删除个数 0表示删除所有，需要删除的对象
        return redisTemplate.opsForList().remove(key, 0, values);
    }

    public void listRemoveKey(String key) {
        redisTemplate.delete(key);
    }

    public List listRightPop(String key) {
        return (List) redisTemplate.opsForList().rightPop(key);
    }

    public <T> T get(String key, Class<T> tClass) {
        return (T) (redisTemplate.opsForValue().get(key));
    }

    public <T> void set(String key, T value, Long timeout, TimeUnit timeUnit) {
        redisTemplate.opsForValue().set(key, value);
        if (timeout != null && timeUnit != null) {
            redisTemplate.expire(key, timeout, timeUnit);
        }
    }

    public void delete(String key) {
        redisTemplate.delete(key);
    }

    public Long listRightPushPresent(String key, Object values) {
        return redisTemplate.opsForList().rightPushIfPresent(key, values);
    }


    public void zSetScore(String key, Set<ZSetOperations.TypedTuple<Object>> typedTupleSet) {
        if(null!=redisTemplate.opsForZSet().range(key,0,-1)){
            redisTemplate.delete(key);
        }
        redisTemplate.opsForZSet().add(key, typedTupleSet);
    }

    public Set<ZSetOperations.TypedTuple<Object>> reverseScoreZset(String key, double min, double max, long offset, long count) {
        return redisTemplate.opsForZSet().reverseRangeByScoreWithScores(key, min, max, offset, count);
    }


}
