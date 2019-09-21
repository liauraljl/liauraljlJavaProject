package com.ljl.example.websocket.framework.controller;

import com.ljl.example.common.ErrorCode;
import com.ljl.example.common.Response;
import com.ljl.example.websocket.framework.service.SendMessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by liaura_ljl on 2019/8/5.
 */
@Slf4j
@RestController
@RequestMapping("/ws")
public class WebSocketController {

    @Autowired
    private SendMessageService sendMessageService;

    @PostMapping("sendMsg")
    public Response hello(Long userId,String msg) {
        sendMessageService.sendMsg(userId,msg);
        return new Response(ErrorCode.SUCCESS, "OK");
    }
}
