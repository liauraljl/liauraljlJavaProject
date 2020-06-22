package com.ljl.example.netty.sentinel.client;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author jinlei.li
 * @date 2020/6/22 14:42
 * @description
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Msg {
    private Integer msgId;

    private String content;
}
