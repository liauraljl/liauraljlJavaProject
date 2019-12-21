package test1.designPattern.singletonTest;

/**
 * 单例 饿汉模式
 * Created by liaura_ljl on 2019/7/29.
 */
public class Singleton2 {
    private static Singleton2 instance=new Singleton2();

    private Singleton2(){}

    public static Singleton2 getInstance(){
        return instance;
    }
}
