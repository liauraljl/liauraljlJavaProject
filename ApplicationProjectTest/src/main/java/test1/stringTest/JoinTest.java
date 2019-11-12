package test1.stringTest;

import com.google.common.base.Joiner;
import com.google.common.collect.Lists;

public class JoinTest {
    public static void main(String[] args) {
        String joinerStr="&";
        Long pid=1009L;
        String str1="uk1";
        String str2="ukt1";
        //内部使用Stringbuilder 非线程安全
        String result= Joiner.on(joinerStr).join(Lists.newArrayList(pid.toString(), str1, str2));
        System.out.println(result);
    }
}
