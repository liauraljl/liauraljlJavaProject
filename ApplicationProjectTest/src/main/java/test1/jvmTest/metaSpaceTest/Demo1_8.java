package test1.jvmTest.metaSpaceTest;

import jdk.internal.org.objectweb.asm.ClassWriter;
import jdk.internal.org.objectweb.asm.Opcodes;
import test1.threadTest.producerAndConsumer.disruptorAndBest.demo.demo1.Demo1;

/**
 * Created by liaura_ljl on 2019/12/2.
 */
public class Demo1_8 extends ClassLoader {
    public static void main(String[] args) {
        new Demo1_8().start();
    }

    private void start(){
        try {
            Thread.sleep(20000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        test1();
    }

    /**
     * 演示元空间溢出
     * -XX:MaxMetaspaceSize=10m
     */
    private void test1(){
        int j=0;
        try{
            Demo1_8 test=new Demo1_8();
            for(int i=0;i<10000;i++,j++){
                // ClassWriter 作用是生成类的二进制字节码
                ClassWriter cw=new ClassWriter(0);
                //版本号，public，类名，包名，父类，接口
                cw.visit(Opcodes.V1_8,Opcodes.ACC_PUBLIC,"Class"+i,null,"java/lang/Object",null);
                // 返回 byte[]
                byte[] code=cw.toByteArray();
                // 执行子类的加载
                test.defineClass("Class"+i,code,0,code.length);
            }
        }finally {
            System.out.println(j);
        }
    }
}
