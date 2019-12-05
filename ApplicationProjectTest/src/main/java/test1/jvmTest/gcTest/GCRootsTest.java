package test1.jvmTest.gcTest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by liaura_ljl on 2019/12/4.
 * 利用 MAT  Java Basics-> GCRoots分析
 */
public class GCRootsTest {
    public static void main(String[] args) throws IOException {
        List<Object> gcList=new ArrayList<>();
        gcList.add("a");
        gcList.add("b");
        System.out.println(1);
        //System.in.read();

        gcList=null;

        System.out.println(2);
        System.in.read();
        System.out.println("end...");
    }
}
