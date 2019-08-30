package com.ljl.example.service.redisTest;

import com.ljl.example.mapper.ProcessMapper;
import com.ljl.example.model.Process;
import com.ljl.example.redis.RedisConstant;
import com.ljl.example.redis.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class PipelineTest {

    @Autowired
    private RedisService redisService;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private ProcessMapper processMapper;

    public void batchSetCompareTest(){
        List<Process> processList1=new ArrayList<>();
        List<Process> processList2=new ArrayList<>();
        int m=10000;
        String strComment="测试数据条数:"+m+",";
        for(int i=0;i<m;i++){
            Process process=new Process();
            process.setId(Long.valueOf(i));
            process.setProcessName("process:"+i);
            processList1.add(process);
        }
        for(int i=m;i<2*m;i++){
            Process process=new Process();
            process.setId(Long.valueOf(i));
            process.setProcessName("process:"+i);
            processList2.add(process);
        }
        System.out.println("set操作！！！！！！！！！！");
        long a=System.currentTimeMillis();
        for(int i=0;i<processList1.size();i++){
            redisService.set(String.format(RedisConstant.TEST_PIEPLINE_BATCHSET,processList1.get(i).getId()),processList1.get(i).getProcessName());
        }
        long b=System.currentTimeMillis();
        System.out.println(strComment+"不用管道,set耗时:"+(b-a));

        long c=System.currentTimeMillis();
        redisService.batchSetValue(processList2);
        long d=System.currentTimeMillis();
        System.out.println(strComment+"用管道,set耗时:"+(d-c));

        System.out.println("del操作！！！！！！！！！！");

        long e=System.currentTimeMillis();
        for(int i=0;i<processList1.size();i++){
            redisService.delKey(String.format(RedisConstant.TEST_PIEPLINE_BATCHSET,processList1.get(i).getId()));
        }
        long f=System.currentTimeMillis();
        System.out.println(strComment+"不用管道,del耗时:"+(f-e));


        long g=System.currentTimeMillis();
        Set<String> keys=processList2.stream().map(o->String.format(RedisConstant.TEST_PIEPLINE_BATCHSET,o.getId())).collect(Collectors.toSet());
        redisService.deleteKeys(keys);
        long h=System.currentTimeMillis();
        System.out.println(strComment+"用管道,del耗时:"+(h-g));

        List<Process> processList3=new ArrayList<>();
        for(int i=0;i<m;i++){
            Process process=new Process();
           // process.setId(Long.valueOf(i));
            process.setProcessName("process:"+i);
            processList3.add(process);
        }
        long i=System.currentTimeMillis();
        for(Process process:processList3){
            processMapper.insertSelective(process);
        }
        long j=System.currentTimeMillis();
        System.out.println(strComment+"mysql insert耗时:"+(j-i));


        System.out.println("jieshu ");
    }
}
