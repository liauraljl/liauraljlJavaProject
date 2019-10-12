package test1.singletonTest;

/**
 * 单例 懒汉模式+同步方法
 * Created by liaura_ljl on 2019/7/29.
 */
public class Singleton3 {
    private static Singleton3 instance=null;

    private Singleton3(){}

    public static synchronized Singleton3 getInstance(){
        if(null==instance)
            instance=new Singleton3();
        return instance;
    }
}
