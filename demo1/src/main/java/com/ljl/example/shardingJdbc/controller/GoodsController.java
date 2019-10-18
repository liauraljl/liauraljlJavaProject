package com.ljl.example.shardingJdbc.controller;

import com.alibaba.fastjson.JSONArray;
import com.dangdang.ddframe.rdb.sharding.keygen.KeyGenerator;
import com.ljl.example.mapper.ProcessMapper;
import com.ljl.example.shardingJdbc.mapper.GoodsMapper;
import com.ljl.example.shardingJdbc.model.Goods;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/shardingJdbc/ljl")
public class GoodsController {

    @Autowired
    private GoodsMapper goodsMapper;

    @Autowired
    private ProcessMapper processMapper;

    @GetMapping("save")
    public String save(){
        for(int i= 1 ; i <= 40 ; i ++){
            Goods goods = new Goods();
            goods.setGoodsId((long) i);
            goods.setGoodsName( "shangpin" + i);
            goods.setGoodsType((long) (i+1));
            goodsMapper.insert(goods);
        }
        return "success";
    }

    @GetMapping("selectAll")
    public String select(){
        return goodsMapper.selectAll().toString();
    }

    @GetMapping("deleteAll")
    public void delete(){
        Example example=new Example(Goods.class);
        goodsMapper.deleteByExample(example);
    }

    @GetMapping("query1")
    public String query1(){
        Example example=new Example(Goods.class);
        Example.Criteria criteria=example.createCriteria();
        criteria.andBetween("goodsId",10L,30L);
        List<Goods> goodsList=goodsMapper.selectByExample(example);
        return JSONArray.toJSONString(goodsList);
    }

    @GetMapping("query2")
    public String query2(){
        List<Long> goodsIds = new ArrayList<>();
        goodsIds.add(10L);
        goodsIds.add(15L);
        goodsIds.add(20L);
        goodsIds.add(25L);
        Example example=new Example(Goods.class);
        Example.Criteria criteria=example.createCriteria();
        criteria.andIn("goodsId",goodsIds);
        List<Goods> goodsList=goodsMapper.selectByExample(example);
        return JSONArray.toJSONString(goodsList);
    }

    @GetMapping("selectByPage")
    public String getByPage(Integer pageNum,Integer pageSize){
        int start = (pageNum - 1) * pageSize;
        RowBounds rowBounds = new RowBounds(start, pageSize);
        Example example=new Example(Goods.class);
        Example.Criteria criteria=example.createCriteria();
        criteria.andBetween("goodsId",1L,100L);
        List<Goods> goodsList=goodsMapper.selectByExampleAndRowBounds(example,rowBounds);
        return JSONArray.toJSONString(goodsList);
    }

    @GetMapping("getProcess")
    public String getProcess(){
        return processMapper.selectAll().toString();
    }
}
