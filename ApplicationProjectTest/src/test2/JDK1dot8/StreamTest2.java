package test2.JDK1dot8;

import Test.TestClass.GafeCat;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class StreamTest2 {
    public static void main(String[] args) {
        new StreamTest2().start();
    }

    private void start(){
        GafeCat g1=new GafeCat("name1",1,"green");
        GafeCat g2=new GafeCat("name2",2,"red");
        GafeCat g3=new GafeCat("name3",3,"green");
        List<GafeCat> list=new ArrayList<>();
        list.add(g1);list.add(g2);list.add(g3);
        List<String> strList=new ArrayList<>();
        strList=list.stream().filter(item->item.getColor().equals("green")).map(item->item.getColor()).collect(Collectors.toList());
        System.out.println(strList);
    }
}
