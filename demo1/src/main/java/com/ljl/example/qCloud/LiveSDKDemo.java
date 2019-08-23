package com.ljl.example.qCloud;

import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.cvm.v20170312.CvmClient;
import com.tencentcloudapi.cvm.v20170312.models.DescribeZonesRequest;
import com.tencentcloudapi.cvm.v20170312.models.DescribeZonesResponse;

/**
 * Created by liaura_ljl on 2019/8/17.
 */
public class LiveSDKDemo {

    private static String secretId="AKIDpRaYWD2sprhJmMymbEgmfJjtMdewrpSs";

    //@Value("SecretKey")
    private static String secretKey="LQJ1TkmmRrhGiV3RaT7YZLySvJfyBII3";
    public static void main(String[] args){
        /*try{
            // 实例化一个认证对象，入参需要传入腾讯云账户secretId，secretKey
            Credential cred = new Credential(secretId, secretKey);

            // 实例化要请求产品(以cvm为例)的client对象
            ClientProfile clientProfile = new ClientProfile();
            clientProfile.setSignMethod(ClientProfile.SIGN_TC3_256);
            CvmClient client = new CvmClient(cred, "ap-guangzhou", clientProfile);

            // 实例化一个请求对象
            DescribeZonesRequest req = new DescribeZonesRequest();


            // 通过client对象调用想要访问的接口，需要传入请求对象
            DescribeZonesResponse resp = client.DescribeZones(req);

            // 输出json格式的字符串回包
            System.out.println(DescribeZonesRequest.toJsonString(resp));
        } catch (TencentCloudSDKException e) {
            System.out.println(e.toString());
        }*/


    }
}
