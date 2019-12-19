//package com.ljl.example.elasticsearch.controller;
//
//import com.ljl.example.elasticsearch.model.Item;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequestMapping("item")
//public class ItemController {
//
//    //@Autowired
//    private ElasticsearchTemplate elasticsearchTemplate;
//
//    @RequestMapping("createIndex")
//    public void createIndex(){
//        elasticsearchTemplate.createIndex(Item.class);
//        //elasticsearchTemplate.putMapping(Item.class);
//    }
//
//    @RequestMapping("deleteIndex")
//    public void deleteIndex(){
//        elasticsearchTemplate.deleteIndex(Item.class);
//    }
//
//
//}
