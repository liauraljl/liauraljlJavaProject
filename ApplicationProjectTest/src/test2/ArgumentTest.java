package test2;

import java.util.ArrayList;
import java.util.List;

public class ArgumentTest {
    public static void main(String[] args) {
           ArgumentTest argumentTest=new ArgumentTest();
           List<String> list=new ArrayList<>();
           for(int i=1;i<10;i++)
            list.add(i+"");
           String[] stra={"1","2","3"};
           argumentTest.test1("1","2");
           argumentTest.test1(stra);
           //argumentTest.test1(list); xx

    }

    private void test1(String... str){
        System.out.println("test1");
        for(String item:str)
            System.out.println(item);
    }

    private void test2(Integer a,String... str){
        System.out.println("test2");
    }
}
