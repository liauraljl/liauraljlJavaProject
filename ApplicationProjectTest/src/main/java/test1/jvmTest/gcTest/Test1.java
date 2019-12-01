package test1.jvmTest.gcTest;

public class Test1 {
    public static void main(String[] args) {
        new Test1().start();
    }

    private void start(){
        try {
            test1();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void test1() throws InterruptedException {
        System.out.println("1...");
        Thread.sleep(30000);
        byte[] arr=new byte[1024*1024*40];
        byte[] arr2=new byte[1024*1024*60];
        System.out.println("2...");
        Thread.sleep(30000);
        //arr=null;
        System.gc();
        System.out.println("3...");
        Thread.sleep(100000L);
    }
}
