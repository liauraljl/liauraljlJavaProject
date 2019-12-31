package test1.threadTest.threadLocalTest;

import java.util.concurrent.TimeUnit;

public class InheritableThreadLocalTest {

    public static ThreadLocal<Integer> threadLocal = new ThreadLocal<Integer>();
    public static ThreadLocal<Integer> inheritableThreadLocal = new InheritableThreadLocal<Integer>();
    public static ThreadLocal<User> mutableInheritableThreadLocal = new InheritableThreadLocal<User>();
    public static ThreadLocal<User> mutableInheritableThreadLocalTo = new InheritableThreadLocal<User>();
    public static ThreadLocal<String> immutableInheritableThreadLocal = new InheritableThreadLocal<String>();
    public static ThreadLocal<String> immutableInheritableThreadLocalTo = new InheritableThreadLocal<String>();

    public static void main(String args[]) throws InterruptedException {
        // 测试0.ThreadLocal普通测试;
        // 结论0: ThreadLocal下子线程获取不到父线程的值
        threadLocal.set(new Integer(123)); // 父线程初始化

        Thread thread = new MyThread();
        thread.start();

        TimeUnit.MILLISECONDS.sleep(100); // 睡眠, 以等待子线程执行完毕
        System.out.println("main = " + threadLocal.get());
        System.out.println();

        // 测试1.InheritableThreadLocal普通测试;
        // 结论1: InheritableThreadLocal下子线程可以获取父线程的值
        inheritableThreadLocal.set(new Integer(123)); // 父线程初始化

        Thread threads = new MyThreadTo();
        threads.start();

        TimeUnit.MILLISECONDS.sleep(100); // 睡眠, 以等待子线程执行完毕
        System.out.println("main = " + inheritableThreadLocal.get());
        System.out.println();

        // 测试2.父线程和子线程的传递关系测试: 可变对象, 父线程初始化;
        // 结论2: 父线程初始化, Thread Construct浅拷贝, 共用索引, 子线程先get()对象, 再修改对象的属性,
        // 父线程跟着变, 注意: 此处子线程如果没有先get()直接使用set()一个新对象, 父线程是不会跟着变的
        mutableInheritableThreadLocal.set(new User("joon"));// 2.1父线程初始化

        Thread TestThread = new TestThread(); // 2.2先初始化父线程再创建子线程, 确保子线程能继承到父线程的User
        TestThread.start(); // 开始执行子进程

        TimeUnit.MILLISECONDS.sleep(100); // 睡眠, 以等待子线程执行完毕
        System.out.println("main = " + mutableInheritableThreadLocal.get()); // 2.5此处输出值为子线程修改的值, 因此可得出上述结论2
        System.out.println();

        // 测试3.父线程和子线程的传递关系测试: 可变对象, 父线程不初始化;
        // 结论3: 父线程没有初始化, 子线程初始化, 无Thread Construct浅拷贝, 子线程和主线程都是单独引用, 不同对象,
        // 子线程修改父线程不跟着变
        Thread TestThreadTo = new TestThreadTo(); // 3.1创建子进程
        TestThreadTo.start();

        TimeUnit.MILLISECONDS.sleep(100); // 睡眠, 以等待子线程执行完毕
        System.out.println("main = " + mutableInheritableThreadLocalTo.get()); // 3.3此处输出为null, 可得出上述结论3
        System.out.println();

        // 测试4.父线程和子线程的传递关系测试: 不可变对象, 父线程初始化;
        // 结论4: 父线程初始化, Thread Construct浅拷贝, 但由于不可变对象由于每次都是新对象, 所以互不影响
        immutableInheritableThreadLocal.set("joon");// 4.1父线程初始化

        Thread TestThreadTre = new TestThreadTre(); // 4.2先初始化父线程再创建子线程, 确保子线程能继承到父线程的值
        TestThreadTre.start(); // 执行子进程

        TimeUnit.MILLISECONDS.sleep(100); // 睡眠, 以等待子线程执行完毕
        System.out.println("main = " + immutableInheritableThreadLocal.get()); // 4.5此处输出为父线程的值, 因此可得出上述结论4
        System.out.println();

        // 测试5.父线程和子线程的传递关系测试: 不可变对象, 父线程不初始化;
        // 结论5: 父线程没有初始化, 子线程初始化, 无Thread Construct浅拷贝, 子线程和父线程操作不同对象, 互不影响
        Thread TestThreadFour = new TestThreadFour(); // 5.1创建子线程
        TestThreadFour.start();

        TimeUnit.MILLISECONDS.sleep(100); // 睡眠, 以等待子线程执行完毕
        System.out.println("main = " + immutableInheritableThreadLocalTo.get()); // 5.3此处输出为空, 因此可得出上述结论5
    }

    private static class MyThread extends Thread {
        @Override
        public void run() {
            System.out.println("MyThread = " + threadLocal.get());
        }
    }

    private static class MyThreadTo extends Thread {
        @Override
        public void run() {
            System.out.println("inheritableThreadLocal = " + inheritableThreadLocal.get());
        }
    }

    private static class TestThread extends Thread {
        @Override
        public void run() {
            // 2.3此处输出父线程的初始化对象值, 代表子线程确实继承了父线程的对象值
            System.out.println("TestThread.before = " + mutableInheritableThreadLocal.get());
            // 2.4子类拿到对象并修改
            mutableInheritableThreadLocal.get().setName("whee");
            System.out.println("mutableInheritableThreadLocal = " + mutableInheritableThreadLocal.get());
        }
    }

    private static class TestThreadTo extends Thread {
        @Override
        public void run() {
            mutableInheritableThreadLocalTo.set(new User("whee"));// 3.2子线程调用set方法
            System.out.println("mutableInheritableThreadLocalTo = " + mutableInheritableThreadLocalTo.get());
        }
    }

    private static class TestThreadTre extends Thread {
        @Override
        public void run() {
            // 4.3此处输出父线程初始化的值, 代表子线程确实继承了父线程的对象值
            System.out.println("TestThreadTre.before = " + immutableInheritableThreadLocal.get());
            // 4.4子线程调用set方法
            immutableInheritableThreadLocal.set("whee");
            System.out.println("immutableInheritableThreadLocal = " + immutableInheritableThreadLocal.get());
        }
    }

    private static class TestThreadFour extends Thread {
        @Override
        public void run() {
            immutableInheritableThreadLocalTo.set("whee");// 5.2子线程调用set方法
            System.out.println("immutableInheritableThreadLocalTo = " + immutableInheritableThreadLocalTo.get());
        }
    }

    private static class User {
        String name;

        public User(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "User [name=" + name + "]";
        }
    }
}
