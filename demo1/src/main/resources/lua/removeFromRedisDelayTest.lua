-- 删除zset中数据
redis.call("ZREM", KEYS[1], ARGV[1]);