package com.ljl.example.kafka;

import org.apache.kafka.common.serialization.Deserializer;

import java.util.Map;

/**
 * @author baiyu
 * @description: MapDeserialization
 * @date: 2019/5/24
 */
public class MapDeserialization implements Deserializer<Map> {
    private KryoUtils kryoUtils;

    public MapDeserialization() {

    }
    private KryoUtils getKryoUtils(){
        if(null != kryoUtils){
            return kryoUtils;
        }
        kryoUtils = new KryoUtils();
        return kryoUtils;
    }

    @Override
    public void configure(Map<String, ?> var1, boolean var2){

    }

    @Override
    public Map deserialize(String var1, byte[] message){

        KryoUtils kryoUtils = new KryoUtils();
        Map<String,String> map = kryoUtils.bytesToMap(message);
        return map;
    }

    @Override
    public void close(){

    }

}
