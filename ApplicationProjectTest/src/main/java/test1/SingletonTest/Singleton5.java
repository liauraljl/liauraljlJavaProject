package test1.SingletonTest;

/**
 * 单例 懒汉模式i+Double Check+volatile
 * Created by liaura_ljl on 2019/7/29.
 */
public class Singleton5 {
    private static volatile Singleton5 instance=null;

    private Singleton5(){}

    public static Singleton5 getInstance(){
        if(null==instance){
            synchronized (Singleton5.class){
                if(null==instance){
                    instance=new Singleton5();
                }
            }
        }
        return instance;
    }


}
