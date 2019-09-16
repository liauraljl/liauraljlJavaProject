package test1.ThreadTest.juc;

public class ThreadLocalTest{

    public static ThreadLocal<Integer> t1=new ThreadLocal<Integer>();

    public static void main(String[] args) {
        t1.set(1);
        for(int i=0;i<1000;i++){
            new Thread(() -> {
                Double d=Math.random()*10000;
                //int a=t1.get();
                t1.set(d.intValue());
                new A().get();
                new B().get();
            }).start();
        }
    }

    static class A{
        public void get(){
            /*取得当前线程所需要的值*/
            System.out.println(t1.get());
        }
    }
    static class B{
        public void get(){
            /*取得当前线程所需要的值*/
            System.out.println(t1.get());
        }
    }
}
