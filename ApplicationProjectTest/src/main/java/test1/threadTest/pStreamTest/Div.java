package test1.threadTest.pStreamTest;

import java.util.Comparator;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

public class Div implements Runnable {

    public static BlockingQueue<Msg> bq=new LinkedBlockingDeque<Msg>();

    public static long a=0;

    @Override
    public void run() {
        while (true){
            try{
                Msg msg=bq.take();
                msg.i=msg.i/2;
                System.out.println(msg.orgStr+"="+msg.i);
                long b=System.currentTimeMillis();
                System.out.println((b-a)/1000+"s");
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}
