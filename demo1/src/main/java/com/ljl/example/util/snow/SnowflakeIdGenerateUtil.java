package com.ljl.example.util.snow;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @description: SnowflakeIdGenerateUtil
 */
public class SnowflakeIdGenerateUtil {

    private static final SnowflakeIdWorker SNOWFLAKE_ID_WORKER;

    static {
        InetAddress address;
        try {
            address = InetAddress.getLocalHost();
        } catch (final UnknownHostException e) {
            throw new IllegalStateException("Cannot get LocalHost InetAddress, please check your network!");
        }
        byte[] ipAddressByteArray = address.getAddress();
        //根据机器IP获取工作进程Id,如果线上机器的IP二进制表示的最后10位不重复,建议使用此种方式
        // ,列如机器的IP为192.168.1.108,二进制表示:11000000 10101000 00000001 01101100
        // ,截取最后10位 01 01101100,转为十进制364,设置workerId为364.
        long workerId = (long) (ipAddressByteArray[ipAddressByteArray.length - 1] & 0x1F);
        SNOWFLAKE_ID_WORKER = new SnowflakeIdWorker(workerId, 0);
    }

    /**
     * 获取全局自增ID
     * @return
     */
    public static Long getGeneratorKey() {
        return SNOWFLAKE_ID_WORKER.nextId();
    }

}
