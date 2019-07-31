package com.ljl.example.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Table(name = "t_process")
@Data
public class Process {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "pid")
    private Long pid;

    @Column(name = "process_name")
    private String processName;

    @Column(name = "process_flag")
    private Integer processFlag;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "update_time")
    private Date updateTime;

    @Column(name = "deleted")
    private Integer deleted;

    @Column(name = "process_digraph")
    private String processDigraph;
}