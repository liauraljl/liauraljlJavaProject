package com.ljl.example.pattern.service;

import com.ljl.example.pattern.model.User;
import org.springframework.stereotype.Service;

@Service
public class ServiceImplA {

    public void test1(){
        User user=User.builder().name("Miss li").age(25).build();

    }
}
