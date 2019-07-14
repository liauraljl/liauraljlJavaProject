package com.ljl.example.test2.impl;

import com.ljl.example.test2.Test2Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by liaura_ljl on 2019/7/14.
 */
@Service
public class Test3ServiceImpl {
    @Autowired
    private Test2Service test2Service;

    public void test3(){
        test2Service.test2();
    }

}
