package test1.threadTest.exceptionTest;

/**
 * Created by liaura_ljl on 2019/12/10.
 */
public class UncaughtExceptionTest implements Runnable{

    private String name;

    public UncaughtExceptionTest(String name){
        this.name=name;
    }

    @Override
    public void run() {
        throw new RuntimeException();
    }

    public static void main(String[] args) throws InterruptedException {
        try{
            Thread.setDefaultUncaughtExceptionHandler(new MyUncaughtExceptionHandler());
            new Thread(new UncaughtExceptionTest("MyThread-1")).start();
            Thread.sleep(200);
            new Thread(new UncaughtExceptionTest("MyThread-2")).start();
        }catch (Throwable e){//不需要try catch 用不上
            System.out.println(":sdsds!!!!!!!!!!!!!!!");
            e.printStackTrace();
        }
    }
}
