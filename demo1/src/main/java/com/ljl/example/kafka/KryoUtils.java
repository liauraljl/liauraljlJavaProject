package com.ljl.example.kafka;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.esotericsoftware.kryo.serializers.MapSerializer;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

public class KryoUtils {

    private  Kryo kryo = new Kryo();
    private Output output = new Output(2048, -1);
    private Input input = new Input();
    public KryoUtils(){
        kryo.register(HashMap.class, new MapSerializer());
    }

    public byte[] mapToBytes(Map map) {
        kryo.writeClassAndObject(output, map);
        output.close();
        byte[] bytes = output.toBytes();
        output.flush();
        output.clear();
        return bytes;
    }

    public String mapToString(Map map){
        kryo.writeClassAndObject(output,map);
        output.close();
        String content = output.toString();
        output.flush();
        output.clear();
        return content;
    }

    public Map<String, String> bytesToMap(byte[] bytes) {
        input.setBuffer(bytes);
        Map map = (Map) kryo.readClassAndObject(input);
        return map;
    }

    public static void main(String[] args){
        Map map = new HashMap();
        map.put("aaa","bbbb");
        map.put("cccc","aaa");
        map.put("ddd","cccc");
        CountDownLatch countDownLatch = new CountDownLatch(1);
        for(int i = 1;i< 6;i++){
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    KryoUtils kryoUtils = new KryoUtils();
                    try {
                        countDownLatch.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("start ");
                    kryoUtils.bytesToMap(kryoUtils.mapToBytes(map));
                }
            });
            thread.start();
        }
        countDownLatch.countDown();
    }

}
