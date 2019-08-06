package com.ljl.example.elasticsearch.dao;

import com.ljl.example.elasticsearch.model.Employee;
import com.ljl.example.elasticsearch.model.Item;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Component;

@Component
public interface ItemRepository extends ElasticsearchRepository<Item,String> {
    /**
     * @param id
     * @return
     */
    Item queryEmployeeById(Long id);
}
