package com.ljl.example.sentinel;

import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import com.ljl.example.util.DateUtil;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by liaura_ljl on 2019/10/16.
 */
@Service
public class SentinelTestServiceImpl {
    /*static {
        List<FlowRule> rules = new ArrayList<>();
        FlowRule rule = new FlowRule();
        rule.setResource("HelloWorld");
        rule.setGrade(RuleConstant.FLOW_GRADE_QPS);
        // Set limit QPS to 20.
        rule.setCount(3);
        rules.add(rule);
        FlowRuleManager.loadRules(rules);
    }*/

    public static void main(String[] args){
        int count=0;

        while (true) {
            // 1.5.0 版本开始可以直接利用 try-with-resources 特性
            try (Entry entry = SphU.entry("HelloWorld")) {
                // 被保护的逻辑
                System.out.println(DateUtil.dateToStr(new Date(),"yyyy-MM-dd HH:mm:ss")+"hello world,"+(++count));
            } catch (BlockException ex) {
                count=0;
                // 处理被流控的逻辑
                //System.out.println("blocked!");
            }
        }
    }

    @SentinelResource("HelloWorld")
    public void helloWorld() {
        // 资源中的逻辑
        System.out.println(DateUtil.dateToStr(new Date(),"yyyy-MM-dd HH:mm:ss")+"hello world");
    }

    /**
     * 配置规则
     */
    @PostConstruct
    public void initFlowRules(){
        List<FlowRule> rules = new ArrayList<>();
        FlowRule rule = new FlowRule();
        rule.setResource("HelloWorld");
        rule.setGrade(RuleConstant.FLOW_GRADE_QPS);
        // Set limit QPS to 20.
        rule.setCount(3);
        rules.add(rule);
        FlowRuleManager.loadRules(rules);
        System.out.println("初始化Sentinel配置成功！");
    }
}
