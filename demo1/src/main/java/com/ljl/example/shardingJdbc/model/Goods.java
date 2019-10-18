package com.ljl.example.shardingJdbc.model;

import lombok.Data;
import org.springframework.data.annotation.Id;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="goods")
@Data
public class Goods {
    @Id
    private Long goodsId;

    private String goodsName;

    private Long goodsType;
}
