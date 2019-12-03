package test1.jvmTest.directMemoryTest;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by liaura_ljl on 2019/12/3.
 */
public class OOMTest {
    public static void main(String[] args) {
        new OOMTest().start();
    }

    private void start(){
        test1();
    }

    /**
     * 直接内存溢出
     */
    private void test1(){
        int _100Mb=1024*1024*100;
        List<ByteBuffer> list=new ArrayList<>();
        int i=0;
        try{
            while (true){
                ByteBuffer byteBuffer=ByteBuffer.allocateDirect(_100Mb);//分配直接内存//allocate分配堆内存
                list.add(byteBuffer);
                i++;
            }
        }finally {
            System.out.println(i);
        }
    }
}
