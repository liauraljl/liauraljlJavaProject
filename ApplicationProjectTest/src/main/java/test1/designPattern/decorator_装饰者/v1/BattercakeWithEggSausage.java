package test1.designPattern.decorator_装饰者.v1;

public class BattercakeWithEggSausage extends BattercakeWithEgg {
    public String getDesc(){
        return super.getDesc()+" 加一个香肠";
    }

    public int getCost(){
        return super.getCost()+2;
    }
}
