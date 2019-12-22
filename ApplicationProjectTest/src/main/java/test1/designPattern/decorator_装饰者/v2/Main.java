package test1.designPattern.decorator_装饰者.v2;

/**
 * 装饰者模式
 */
public class Main {
    public static void main(String[] args) {
        ABattercake aBattercake;
        aBattercake=new Battercake();
        aBattercake=new EggDecorator(aBattercake);
        aBattercake=new SausageDecorator(aBattercake);
        aBattercake=new SausageDecorator(aBattercake);
        System.out.println(aBattercake.getDesc()+":"+aBattercake.getCost());
    }
}
