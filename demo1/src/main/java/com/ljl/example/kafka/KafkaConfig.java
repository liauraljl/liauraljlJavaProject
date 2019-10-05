package com.ljl.example.kafka;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.*;
import org.springframework.kafka.listener.ContainerProperties;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConfig {

    @Value("${kafka.bootstrapServers}")
    private String bootstrapServers;

    @Value("${kafka.consumer.groupId}")
    private String consumerGroupId;

    @Value("${kafka.consumer.enableAutoCommit}")
    private String consumerEnableAutoCommit;

    @Value("${kafka.consumer.autoCommitIntervalMs}")
    private String consumerAutoCommitIntervalMs;

    @Value("${kafka.consumer.sessionTimeoutMs}")
    private String consumerSessionTimeoutMs;

    @Value("${kafka.consumer.maxPollRecords}")
    private String consumerMaxPollRecords;

    @Value("${kafka.consumer.autoOffsetReset}")
    private String consumerAutoOffsetReset;

    @Value("${kafka.consumer.concurrency2}")
    private int concurrency;

    /**--------------------producer----------------------*/
    @Value("${kafka.producer.retries}")
    private int retries;

    @Value("${kafka.producer.batch.size}")
    private int batchSize;

    @Value("${kafka.producer.linger}")
    private int linger;

    @Value("${kafka.producer.buffer.memory}")
    private int bufferMemory;

    @Value("${kafka.producer.acks}")
    private String acks;


    public Map<String, Object> producerConfigs() {
        Map<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put(ProducerConfig.RETRIES_CONFIG, retries);
        props.put(ProducerConfig.BATCH_SIZE_CONFIG, batchSize);
        props.put(ProducerConfig.LINGER_MS_CONFIG, linger);
        props.put(ProducerConfig.BUFFER_MEMORY_CONFIG, bufferMemory);
        props.put(ProducerConfig.ACKS_CONFIG, acks);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        return props;
    }

    public ProducerFactory<String, String> producerFactory() {
        return new DefaultKafkaProducerFactory<>(producerConfigs());
    }

    @Bean
    public KafkaTemplate<String, String> kafkaTemplate() {
        return new KafkaTemplate<String, String>(producerFactory());
    }

    /**
     * ConsumerFactory
     * @return
     */
    @Bean
    public ConsumerFactory<Object, Object> consumerFactory(){
        //参数
        Map<String, Object> configs = new HashMap<String, Object>();
        configs.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        configs.put(ConsumerConfig.GROUP_ID_CONFIG, consumerGroupId);
        configs.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, consumerEnableAutoCommit);
        configs.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, consumerAutoCommitIntervalMs);
        configs.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, consumerSessionTimeoutMs);
        //批量消费数量
        configs.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, consumerMaxPollRecords);
        configs.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, consumerAutoOffsetReset);
        configs.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        configs.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,StringDeserializer.class);//消息体 String Map Entity
        //configs.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,MapDeserialization.class);
        return new DefaultKafkaConsumerFactory<Object, Object>(configs);
    }

    /**
     * 添加KafkaListenerContainerFactory，用于批量消费消息
     * @return
     */
    @Bean
    public KafkaListenerContainerFactory<?> batchContainerFactory(){
        ConcurrentKafkaListenerContainerFactory<Object, Object> containerFactory = new ConcurrentKafkaListenerContainerFactory<Object, Object>();
        containerFactory.setConsumerFactory(consumerFactory());
//        //线程池大小
       containerFactory.setConcurrency(concurrency);
        //批量消费
        containerFactory.setBatchListener(true);
        containerFactory.getContainerProperties().setAckMode(ContainerProperties.AckMode.MANUAL_IMMEDIATE);
        return containerFactory;
    }

    /*public ProducerFactory<String, MessageEntity> producerFactory() {
        return new DefaultKafkaProducerFactory<>(producerConfigs(),
                new StringSerializer(),
                new JsonSerializer<MessageEntity>());
    }

    //@Bean
    public KafkaTemplate<String, MessageEntity> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }*/

}

