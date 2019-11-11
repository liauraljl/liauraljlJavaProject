package com.ljl.example.service.other.impl;

import com.ljl.example.service.other.Test2Service;
import org.springframework.stereotype.Service;

/**
 * Created by liaura_ljl on 2019/7/14.
 */
@Service
public class Test2ServiceImpl implements Test2Service {
    public String test2() throws Exception {
        long time=System.currentTimeMillis();
        if(time/1000000000000L==1){
            //System.err.println("时间戳:"+time);
            throw new Exception();
        }
        return "success";
    }
}
