package test1.threadTest.jmmTest;

/**
 * Created by liaura_ljl on 2019/12/8.
 * 可见性 测试
 */
public class VolatileTest {

    private static boolean run=true;

    private static volatile boolean vRun=true;

    public static void main(String[] args) {
        new VolatileTest().start();
    }

    private void start(){
        test1() ;
        //test2() ;
    }

    private void test1(){
        new Thread(()->{
            while (run){
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //System.out.println("running!!!!!!!!!!!!!");//println()使用了synchronized synchronized也是强制读取主存  synchronized是重量级操作，性能低
            }
        }).start();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        run=false;
        System.err.println("修改完成！");
    }

    private void test2(){
        new Thread(()->{
            while (vRun){

            }
        }).start();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        vRun=false;
        System.err.println("修改完成！");
    }
}
