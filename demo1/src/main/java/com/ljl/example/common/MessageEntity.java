package com.ljl.example.common;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Data
public class MessageEntity implements Serializable {
    private static final long serialVersionUID=1L;
    private String title;
    private String body;
}
