package com.ljl.example.redis.delayqueue;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by liaura_ljl on 2019/9/6.
 */
public class Order {
    private String createdTime;

    public Order() {
        this.createdTime = new SimpleDateFormat("hh:mm:ss").format(new Date());
    }

    public String getCreatedTime() {
        return createdTime;
    }
}
