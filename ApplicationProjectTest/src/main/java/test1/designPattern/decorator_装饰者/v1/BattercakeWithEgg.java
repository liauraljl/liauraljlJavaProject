package test1.designPattern.decorator_装饰者.v1;

public class BattercakeWithEgg extends Battercake {
    public String getDesc(){
        return super.getDesc()+" 加一个鸡蛋";
    }

    public int getCost(){
        return super.getCost()+1;
    }
}
