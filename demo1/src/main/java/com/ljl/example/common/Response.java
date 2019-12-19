package com.ljl.example.common;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Response {
    private String code;
    private String message;

    public Response(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
