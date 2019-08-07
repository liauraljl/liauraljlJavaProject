package com.ljl.example.elasticsearchTest;

import com.ljl.example.Demo1Application;
import com.ljl.example.elasticsearch.dao.ItemRepository;
import com.ljl.example.elasticsearch.model.Item;
import com.ljl.example.elasticsearch.service.ItemService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by liaura_ljl on 2019/8/6.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Demo1Application.class)
public class ElasticSearchTest {

    @Autowired
    private ItemService itemService;
    /**
     * 定义新增方法
     */
    @Test
    public void insertTest(){
        itemService.insertTest();
    }

    /**
     * 定义批量新增方法
     */
    @Test
    public void insertList() {
        itemService.insertList();
    }

    /**
     * 定义修改方法
     */
    @Test
    public void update(){
        itemService.update();
    }
    /**
     * 定义删除方法
     */
    @Test
    public void delete(){
        itemService.delete();
    }

    /**
     * 定义查询方法,含对价格的降序、升序查询
     */
    @Test
    public void testQueryAll(){
        itemService.testQueryAll();
    }

    @Test
    public void queryByPriceBetween(){
        itemService.queryByPriceBetween();
    }
}
