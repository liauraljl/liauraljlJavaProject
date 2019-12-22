package test1.designPattern.decorator_装饰者.v1;

/**
 * 继承（并非装饰者模式）
 */
public class Main {
    public static void main(String[] args) {
        Battercake battercake=new Battercake();
        System.out.println(battercake.getDesc()+":"+battercake.getCost());

        BattercakeWithEgg battercakeWithEgg=new BattercakeWithEgg();
        System.out.println(battercakeWithEgg.getDesc()+":"+battercakeWithEgg.getCost());

        BattercakeWithEggSausage battercakeWithEggSausage=new BattercakeWithEggSausage();
        System.out.println(battercakeWithEggSausage.getDesc()+":"+battercakeWithEggSausage.getCost());

        //问题：多个鸡蛋和香肠需要更多子类  复杂
    }
}
