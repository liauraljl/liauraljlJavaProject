package test1.jvmTest.directMemoryTest;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Created by liaura_ljl on 2019/12/3.
 * 演示 ByteBuffer 作用
 */
public class ByteBufferTest {
    static final String FROM="F:\\newTzMaterialTemp.rar";
    static final String TO="F:\\test.rar";
    static final int _1Mb=1024*1024;

    public static void main(String[] args) {
        io();//io用时:2860.231099
        directBuffer();//directBuffer用时:718.2186
    }

    /**
     * 直接内存
     */
    private static void directBuffer(){
        long start=System.nanoTime();
        try(FileChannel from=new FileInputStream(FROM).getChannel();FileChannel to=new FileOutputStream(TO).getChannel()){
            ByteBuffer bb=ByteBuffer.allocateDirect(_1Mb);
            while (true){
                int len=from.read(bb);
                if(len == -1){
                    break;
                }
                bb.flip();
                to.write(bb);
                bb.clear();
            }

        }catch (IOException e){
            e.printStackTrace();
        }
        long end=System.nanoTime();
        System.out.println("directBuffer用时:"+(end-start)/1000_000.0);
    }

    private static void io(){
        long start=System.nanoTime();
        try(FileInputStream from=new FileInputStream(FROM);FileOutputStream to=new FileOutputStream(TO)){
            byte[] by=new byte[_1Mb];
            while (true){
                int len=from.read(by);
                if(len == -1){
                    break;
                }
                to.write(by,0,len);
            }

        }catch (IOException e){
            e.printStackTrace();
        }
        long end=System.nanoTime();
        System.out.println("io用时:"+(end-start)/1000_000.0);
    }


}
