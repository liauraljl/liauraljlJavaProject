package com.ljl.example.controller;

import com.ljl.example.common.ErrorCode;
import com.ljl.example.common.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by liaura_ljl on 2019/8/5.
 */
@Slf4j
@RestController
@RequestMapping("/test")
public class TestController {

    @RequestMapping(value = "/hello", method = RequestMethod.GET, produces = {"application/json"})
    public Response hello() {
        return new Response(ErrorCode.SUCCESS.getErrorCode(), "OK");
    }
}
