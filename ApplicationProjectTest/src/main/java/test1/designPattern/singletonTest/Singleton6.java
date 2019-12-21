package test1.designPattern.singletonTest;

/**
 * 单例 Holder方式
 * Created by liaura_ljl on 2019/7/29.
 */
public class Singleton6 {

    private Singleton6(){}

    private static class Holder{
        private static Singleton6 instance=new Singleton6();//延迟加载
    }

    public static Singleton6 getInstance(){
        return Holder.instance;
    }
}
