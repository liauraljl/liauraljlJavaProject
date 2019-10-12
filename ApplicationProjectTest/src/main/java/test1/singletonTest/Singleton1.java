package test1.singletonTest;

/**
 * 单例 懒汉模式
 * Created by liaura_ljl on 2019/7/29.
 */
public class Singleton1 {
    private static Singleton1 instance=null;

    private Singleton1(){}

    public static Singleton1 getInstance(){
        if(null==instance)
            instance=new Singleton1();
        return instance;
    }
}
