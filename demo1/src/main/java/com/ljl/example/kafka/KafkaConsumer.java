package com.ljl.example.kafka;

import com.alibaba.fastjson.JSON;
import com.ljl.example.redis.RedisConstant;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * @author baiyu
 * @description: ProcessKafkaConsumer  消费用户行为日志
 * @date: 2019/5/9
 */
@Component
@Slf4j
@SuppressWarnings("unchecked")
public class KafkaConsumer {

    @Value("${kafka.consumer.topic}")
    private String topic;

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 消费数据
     *
     * @param
     * @param ack
     */
    @KafkaListener(topics = "${kafka.consumer.topic}", groupId = "process", containerFactory = "batchContainerFactory")
    public void consumerMsg(List<ConsumerRecord<String, String>> recordList, Acknowledgment ack) {
        try {
            if(!CollectionUtils.isEmpty(recordList)){
                //recordList.forEach(o-> System.out.println(JSON.toJSONString(o.value())));
                doListMsg(recordList);
            }
            System.out.println("消费结束！");
        }catch (Exception e){
            e.printStackTrace();
        } finally{
            log.info("手动提交偏移量开始：topic:{}", topic);
            ack.acknowledge();
            log.info("手动提交偏移量结束：topic:{}", topic);
        }
    }

    /**
     * 批量异步处理行为
     * @param recordList
     */
    private void doListMsg(List<ConsumerRecord<String, String>> recordList){
        try {
            CompletableFuture[] completableFutures = new CompletableFuture[recordList.size()];
            for (int i = 0; i < recordList.size(); i++) {
                final ConsumerRecord<String, String> record = recordList.get(i);
                completableFutures[i] = CompletableFuture.supplyAsync(()-> {
                    try {
                        String msg=record.value();
                        //消息幂等处理
                        //String msgId=JSON.parseObject(JSON.toJSONString(msg)).getString("msgId");
                        String msgId=JSON.parseObject(msg).getString("msgId");
                        String key=String.format(RedisConstant.KAFKA_MSG_KEY,msgId);
                        Boolean check=redisTemplate.opsForValue().setIfAbsent(key,msgId);
                        if(check){
                            //doOneMsg(record);
                            log.info("doListMsg kafka one,data:{},partition:{}",JSON.toJSONString(msg),record.partition());
                            redisTemplate.expire(key,30, TimeUnit.SECONDS);
                        }else{
                            log.info("消息队列的重复消息{}，key已经存在:{}",msg,key);
                        }
                    } catch (Exception e) {
                        log.error("处理数据失败：topic:{},数据：{},error:{}", topic, JSON.toJSONString(record.value()), e.getMessage(), e);
                    }
                    return true;
                });
            }
            //异步阻塞在这里
           CompletableFuture.allOf(completableFutures).join();
        }catch (Exception e){
            log.error("handlerMsgAsy error:{}",e.getMessage(),e);
        }
    }


}
