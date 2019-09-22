package com.ljl.example.netty.websocket.service;

import com.ljl.example.netty.websocket.util.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class WebsocketUtilService {

    @Autowired
    private RedisUtil redisUtil;

    /*public String getToken() {
        Long tokenLong = SnowFlakeUtils.get().nextId();
        String token = tokenLong.toString();
        String key = String.format(RedisKeyConstant.AUTHKEY, token);
        log.info("发放token:{}", key);
        redisUtil.setString(key, key);
        redisUtil.setExpireTime(key, 10, TimeUnit.SECONDS);
        return token;
    }

    public Boolean getWidFromRedis(String redisKey) {
        Assert.notNull(redisKey, "入参不能为空！");
        String[] split = redisKey.split(":");
        if (split == null || split.length == 0) {
            log.error("格式校验错误！");
            return false;
        }
        List<Long> nows = NettyConnectionUtil.userChannelMap.keySet().stream().collect(Collectors.toList());
        String widString = CollectionUtils.isNotEmpty(nows) ? Joiner.on(",").join(nows) : "";
        String redisVal = redisUtil.getString(redisKey);
        String string = StringUtils.isEmpty(redisVal) ? "" : redisVal + ",";
        redisUtil.setString(redisKey, string + widString, 20L, TimeUnit.SECONDS);

        return true;
    }
*/
}
