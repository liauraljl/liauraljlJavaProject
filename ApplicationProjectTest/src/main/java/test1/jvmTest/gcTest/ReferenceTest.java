package test1.jvmTest.gcTest;

import java.io.IOException;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by liaura_ljl on 2019/12/6.
 */
public class ReferenceTest {
    
    private static final int _4MB=4*1024*1024;
    
    public static void main(String[] args) {
        new ReferenceTest().start();
    }

    private void start() {
        try {
            //test1();
            test2();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 软引用 演示
     * -Xmx20m -XX:+PrintGCDetails -verbose:gc
     */
    private void test1() throws IOException {
        /*List<byte[]> list=new ArrayList<>();
        for(int i=0;1<6;i++){
            list.add(new byte[_4MB]);
        }
        //System.in.read();*/

        //softReference();
        softReference2();
    }

    private void softReference(){
        List<SoftReference<byte[]>> list=new ArrayList<>();
        for(int i=0;i<6;i++){
            SoftReference<byte[]> ref=new SoftReference<>(new byte[_4MB]);
            System.out.println(ref.get());
            list.add(ref);
            System.out.println(list.size());
        }

        System.out.println("循环次数："+list.size());
        for(SoftReference<byte[]> ref:list){
            System.out.println(ref.get());
        }

        //GC info
        /**
         *
         [B@76ed5528
         1
         [B@2c7b84de
         2
         [B@3fee733d
         3
         [GC (Allocation Failure) [PSYoungGen: 2586K->504K(6144K)] 14874K->13235K(19968K), 0.0068226 secs] [Times: user=0.00 sys=0.00, real=0.01 secs]
         [B@5acf9800
         4
         [GC (Allocation Failure) --[PSYoungGen: 4712K->4712K(6144K)] 17444K->17452K(19968K), 0.0029377 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
         [Full GC (Ergonomics) [PSYoungGen: 4712K->4553K(6144K)] [ParOldGen: 12739K->12651K(13824K)] 17452K->17205K(19968K), [Metaspace: 3457K->3457K(1056768K)], 0.0070282 secs] [Times: user=0.00 sys=0.00, real=0.01 secs]
         [GC (Allocation Failure) --[PSYoungGen: 4553K->4553K(6144K)] 17205K->17205K(19968K), 0.0013120 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
         [Full GC (Allocation Failure) [PSYoungGen: 4553K->0K(6144K)] [ParOldGen: 12651K->802K(8704K)] 17205K->802K(14848K), [Metaspace: 3457K->3457K(1056768K)], 0.0081361 secs] [Times: user=0.00 sys=0.00, real=0.01 secs]
         [B@4617c264
         5
         [B@36baf30c
         6
         循环次数：6
         null
         null
         null
         null
         [B@4617c264
         [B@36baf30c
         Heap
         PSYoungGen      total 6144K, used 4432K [0x00000000ff980000, 0x0000000100000000, 0x0000000100000000)
         eden space 5632K, 78% used [0x00000000ff980000,0x00000000ffdd4378,0x00000000fff00000)
         from space 512K, 0% used [0x00000000fff00000,0x00000000fff00000,0x00000000fff80000)
         to   space 512K, 0% used [0x00000000fff80000,0x00000000fff80000,0x0000000100000000)
         ParOldGen       total 8704K, used 4898K [0x00000000fec00000, 0x00000000ff480000, 0x00000000ff980000)
         object space 8704K, 56% used [0x00000000fec00000,0x00000000ff0c89a0,0x00000000ff480000)
         Metaspace       used 3465K, capacity 4494K, committed 4864K, reserved 1056768K
         class space    used 376K, capacity 386K, committed 512K, reserved 1048576K
         */
    }

    private void softReference2() {
        List<SoftReference<byte[]>> list = new ArrayList<>();

        //引用队列
        ReferenceQueue<byte[]> queue = new ReferenceQueue<>();


        for (int i = 0; i < 6; i++) {
            //关联了引用队列 当软引用所关联的byte[]被回收时 软引用自己会被加入队列
            SoftReference<byte[]> ref = new SoftReference<>(new byte[_4MB], queue);
            System.out.println(ref.get());
            list.add(ref);
            System.out.println(list.size());
        }

        Reference<? extends byte[]> poll = queue.poll();
        while (poll != null) {
            //释放软引用本身占用的内存
            list.remove(poll);
            poll = queue.poll();
        }

        System.out.println("未被回收：" + list.size());
        for (SoftReference<byte[]> ref : list) {
            System.out.println(ref.get());
        }
    }

    /**
     * 弱引用 演示
     * -Xmx20m -XX:+PrintGCDetails -verbose:gc
     */
    private void test2() throws IOException {
        /*List<byte[]> list=new ArrayList<>();
        for(int i=0;1<6;i++){
            list.add(new byte[_4MB]);
        }
        //System.in.read();*/

        weakReference();
    }

    private void weakReference() {
        List<WeakReference<byte[]>> list = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            WeakReference<byte[]> ref = new WeakReference<>(new byte[_4MB]);
            list.add(ref);
            for (WeakReference<byte[]> w : list) {
                System.out.println(w.get());
            }
        }

        System.out.println("循环次数：" + list.size());
    }
}
