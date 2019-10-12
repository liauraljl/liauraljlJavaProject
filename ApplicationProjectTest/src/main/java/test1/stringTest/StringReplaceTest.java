package test1.stringTest;

/**
 * Created by liaura_ljl on 2018/4/10.
 */
public class StringReplaceTest {
    public static void main(String[] args){
        Test1();
    }

    private static void Test1(){
        String a="为将诶欧尼公开免费****,分数据";
        String b="(项目编号：6456457)";
        a=a.replace("****",b);
        System.out.print(a);
    }

}
