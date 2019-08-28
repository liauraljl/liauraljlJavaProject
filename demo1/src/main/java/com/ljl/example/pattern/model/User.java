package com.ljl.example.pattern.model;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Builder(toBuilder = true)
@Data
public class User implements Serializable {
    private static final long serialVersionUID=1l;

    private String name;

    private Integer age;

    private String adress;

    private String telPhone;

    private String eMail;
}
