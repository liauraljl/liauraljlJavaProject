//package com.ljl.example.elasticsearch.dao;
//
//import com.ljl.example.elasticsearch.model.Employee;
//import com.ljl.example.elasticsearch.model.Item;
//import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
//import org.springframework.stereotype.Component;
//
//import java.util.List;
//
//@Component
//public interface ItemRepository extends ElasticsearchRepository<Item,Long> {
//
//    /**
//     * @param id
//     * @return
//     */
//    Item queryEmployeeById(Long id);
//
//    /**
//     * @Description:根据价格区间查询
//     * @Param price1
//     * @Param price2
//     * @Author: https://blog.csdn.net/chen_2890
//     */
//    List<Item> findByPriceBetween(double price1, double price2);
//}
