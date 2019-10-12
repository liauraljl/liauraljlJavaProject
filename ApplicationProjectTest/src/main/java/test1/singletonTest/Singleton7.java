package test1.singletonTest;

/**
 * 单例 枚举
 * Created by liaura_ljl on 2019/7/29.
 */
public class Singleton7 {
    private Singleton7(){}

    private enum EnumHolder{
        INSTANCE;
        private Singleton7 instance;
        EnumHolder(){
            this.instance=new Singleton7();
        }
        private Singleton7 getInstance(){
            return instance;
        }
    }

    public static Singleton7 getInstance(){
        return EnumHolder.INSTANCE.getInstance();
    }
}
