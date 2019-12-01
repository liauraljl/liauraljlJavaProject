package test1.jvmTest.stackOOMTest;

/**
 * Created by liaura_ljl on 2019/12/1.
 */
public class DeadLockTest {
    private static String fileA = "A文件";
    private static String filedB = "B文件";

    public static void main(String[] args) {
        new Thread() { //线程1
            //重写run方法
            public void run() {
                while (true) {
                    synchronized (fileA) {//打开文件A,线程独占fileA
                        System.out.println(this.getName() + ":文件A写入");
                        synchronized (filedB) {
                            System.out.println(this.getName() + ":文件B写入");
                        }
                        System.out.println(this.getName() + ":所有文件保存");
                    }
                }
            }
        }.start();

        new Thread() { //线程2
            //重写run方法
            public void run() {
                while (true) {
                    synchronized (filedB) {//打开文件B,线程独占filedB
                        System.out.println(this.getName() + ":文件B写入");
                        synchronized (fileA) {
                            System.out.println(this.getName() + ":文件A写入");
                        }
                        System.out.println(this.getName() + ":所有文件保存");
                    }
                }
            }
        }.start();
    }

}
