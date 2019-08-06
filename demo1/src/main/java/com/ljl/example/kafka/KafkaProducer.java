package com.ljl.example.kafka;

import com.alibaba.fastjson.JSON;
import com.ljl.example.common.IdWorker;
import com.ljl.example.common.MessageEntity;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.HashMap;
import java.util.Map;

@Component
public class KafkaProducer {
    @Autowired
    @Qualifier("kafkaTemplate")
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    private IdWorker idWorker;

    public void send(String topic,String key,MessageEntity entity){
        Map<String, String> map=new HashMap<>();
        map.put("title",entity.getTitle());
        map.put("body",entity.getBody());
        map.put("msgId",idWorker.nextId()+"");
        String str= JSON.toJSONString(map);
        ProducerRecord<String, String> record = new ProducerRecord<>(topic,key,str);

        long startTime = System.currentTimeMillis();

        ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send(record);
        future.addCallback(new ProducerCallback(startTime, key, str));
    }
}
