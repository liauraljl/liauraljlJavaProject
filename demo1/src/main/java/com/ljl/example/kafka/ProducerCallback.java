package com.ljl.example.kafka;


import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.kafka.support.SendResult;
import org.springframework.lang.Nullable;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Slf4j
public class ProducerCallback implements ListenableFutureCallback<SendResult<String, String>> {

    private final long startTime;
    private final String key;
    private final String message;

    private final Gson gson = new Gson();

    public ProducerCallback(long startTime, String key, String map) {
        this.startTime = startTime;
        this.key = key;
        this.message = map;
    }


    @Override
    public void onSuccess(@Nullable SendResult<String, String> result) {
        if (result == null) {
            return;
        }
        long elapsedTime = System.currentTimeMillis() - startTime;

        RecordMetadata metadata = result.getRecordMetadata();
        if (metadata != null) {
            StringBuilder record = new StringBuilder();
            record.append("message(")
                    .append("key = ").append(key).append(",")
                    .append("message = ").append(gson.toJson(message)).append(")")
                    .append("sent to partition(").append(metadata.partition()).append(")")
                    .append("with offset(").append(metadata.offset()).append(")")
                    .append("in ").append(elapsedTime).append(" ms");
            log.info(record.toString());
        }
    }

    @Override
    public void onFailure(Throwable ex) {
        ex.printStackTrace();
    }

}

