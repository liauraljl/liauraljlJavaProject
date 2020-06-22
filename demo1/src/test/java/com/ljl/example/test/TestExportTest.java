package com.ljl.example.test;


import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import com.ljl.example.Demo1Application;
import com.ljl.example.export.TestExport;
import com.ljl.example.util.SoaResponseUtil;
import com.ljl.note.collection.live.domain.bo.LiveRoomInfoBO;
import com.ljl.note.collection.live.domain.dto.LiveRoomInfoGetDTO;
import com.ljl.note.collection.live.service.LiveRoomExport;
import org.apache.http.message.BasicNameValuePair;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Demo1Application.class)
public class TestExportTest {

    @Autowired
    private LiveRoomExport liveRoomExport;

   /* @Autowired
    private TestExport testExportImpl;*/

    @Test
    public void test1(){
        LiveRoomInfoGetDTO liveRoomInfoGetDTO=new LiveRoomInfoGetDTO();
        liveRoomInfoGetDTO.setRoomId(1L);
        /*LiveRoomInfoBO liveRoomInfoBO= SoaResponseUtil.unpack(liveRoomExport.queryRoomByRoomId(liveRoomInfoGetDTO));
        System.out.println(JSON.toJSONString(liveRoomInfoBO));*/
    }

   /* @Test
    public void test2(){
        NameValuePair[] data = {
                new NameValuePair("startTime",""),
                new NameValuePair("endTime","")

        };

        new BasicNameValuePair();
        *//*String s=SoaResponseUtil.unpack(testExportImpl.getTest());
        System.out.println(s);*//*
    }*/
}
