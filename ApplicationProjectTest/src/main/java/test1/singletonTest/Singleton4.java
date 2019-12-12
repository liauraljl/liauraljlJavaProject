package test1.singletonTest;

/**
 * 单例 懒汉模式+Double Check
 * Created by liaura_ljl on 2019/7/29.
 */
public class Singleton4 {
    private static Singleton4 instance = null;

    private Singleton4() {
    }

    public static Singleton4 getInstance() {
        if (null == instance) {
            synchronized (Singleton4.class) {
                if (null == instance) {
                    instance = new Singleton4();
                }
            }
        }
        return instance;
    }
}
