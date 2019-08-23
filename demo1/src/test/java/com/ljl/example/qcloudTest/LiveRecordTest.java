package com.ljl.example.qcloudTest;

import com.alibaba.fastjson.JSON;
import com.ljl.example.Demo1Application;
import com.ljl.example.util.DateUtil;
import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;
import com.tencentcloudapi.live.v20180801.LiveClient;
import com.tencentcloudapi.live.v20180801.models.CreateLiveRecordRequest;
import com.tencentcloudapi.live.v20180801.models.CreateLiveRecordResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;

/**
 * Created by liaura_ljl on 2019/8/17.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Demo1Application.class)
public class LiveRecordTest {

    @Test
    public void createLiveRecordTest() throws TencentCloudSDKException, UnsupportedEncodingException {
        String secretId="AKIDQ3SbWDzWYVCdHksubrrb7NiDVWVFvWDq";
        String secretKey="YswlSagWC0B7nFRO8nIXXJp55e4JI2XO";
        Credential credential=new Credential(secretId,secretKey);
        LiveClient liveClient=new LiveClient(credential,"ap_shanghai");
        CreateLiveRecordRequest request=new CreateLiveRecordRequest();
        request.setDomainName("58799.livepush.myqcloud.com");
        request.setStreamName("test_livecode001");
        Date start=new Date();
       // request.setStartTime(URLEncoder.encode("2019-08-17 20:41:00","UTF-8"));
        //request.setEndTime(URLEncoder.encode("2019-08-18 20:41:00","UTF-8"));
        request.setStartTime("2019-08-17 20:41:00");
        request.setEndTime("2019-08-18 20:41:00");
        request.setFileFormat("mp4");
        try{

            CreateLiveRecordResponse response=liveClient.CreateLiveRecord(request);
            System.out.println(JSON.toJSONString(response));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void createLiveRecordTest2(){
        try{

            Credential cred = new Credential("AKIDQ3SbWDzWYVCdHksubrrb7NiDVWVFvWDq11", "YswlSagWC0B7nFRO8nIXXJp55e4JI2XO");

            HttpProfile httpProfile = new HttpProfile();
            httpProfile.setEndpoint("live.tencentcloudapi.com");

            ClientProfile clientProfile = new ClientProfile();
            clientProfile.setHttpProfile(httpProfile);

            LiveClient client = new LiveClient(cred, "ap-shanghai", clientProfile);

            String params = "{\"DomainName\":\"58799.livepush.myqcloud.com\",\"StreamName\":\"test_livecode002\",\"StartTime\":\"2019-08-17 20:41:00\",\"EndTime\":\"2019-08-18 20:41:00\",\"FileFormat\":\"mp44\"}";
            CreateLiveRecordRequest req = CreateLiveRecordRequest.fromJsonString(params, CreateLiveRecordRequest.class);

            CreateLiveRecordResponse resp = client.CreateLiveRecord(req);

            System.out.println(CreateLiveRecordRequest.toJsonString(resp));
        } catch (TencentCloudSDKException e) {
            System.out.println(e.toString());
        }

    }
}
