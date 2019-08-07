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

    /**
     * 按照价格区间查询
     */
    @Test
    public void queryByPriceBetween(){
        itemService.queryByPriceBetween();
    }

    /**
     *
     * @Description:matchQuery底层采用的是词条匹配查询
     *@Author: https://blog.csdn.net/chen_2890
     */
    @Test
    public void testMathQuery(){
        itemService.testMathQuery();
    }

    /**
     * @Description:
     * termQuery:功能更强大，除了匹配字符串以外，还可以匹配
     * int/long/double/float/....
     * @Author: https://blog.csdn.net/chen_2890
     */
    @Test
    public void testTermQuery(){
        itemService.testTermQuery();
    }

    /**
     * @Description:布尔查询
     * @Author: https://blog.csdn.net/chen_2890
     */
    @Test
    public void testBooleanQuery(){
        itemService.testBooleanQuery();
    }

    /**
     * @Description:模糊查询
     * @Author: https://blog.csdn.net/chen_2890
     */
    @Test
    public void testFuzzyQuery(){
        itemService.testFuzzyQuery();
    }

    /**
     * @Description:分页查询
     * @Author: https://blog.csdn.net/chen_2890
     */
    @Test
    public void searchByPage(){
        itemService.searchByPage();
    }

    /**
     * @Description:排序查询
     * @Author: https://blog.csdn.net/chen_2890
     */
    @Test
    public void searchAndSort(){
        itemService.searchAndSort();
    }


    /**
     * @Description:按照品牌brand进行分组
     * @Author: https://blog.csdn.net/chen_2890
     */
    @Test
    public void testAgg(){
        itemService.testAgg();
    }

    /**
     * @Description:嵌套聚合，求平均值
     * @Author: https://blog.csdn.net/chen_2890
     */
    @Test
    public void testSubAgg(){
        itemService.testSubAgg();
    }
}
