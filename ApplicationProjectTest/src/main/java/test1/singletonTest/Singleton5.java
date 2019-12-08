package test1.singletonTest;

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
                    /**
                     * 正常分为1、new对象初始化和2、赋值两个步骤  可能存在指令重排
                     * 单线程：1和2顺序无关 指令重排不影响
                     * 多线程：线程1先访问，指令重排后，先赋值，还未初始化，线程2又访问，判断instance不为null，直接返回 拿到的实例地址尚未实例化
                     * 采用volatile进制指令重排
                     */
                }
            }
        }
        return instance;
    }


}
