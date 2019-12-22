package test1.designPattern.decorator_装饰者.v2;

public class Battercake extends ABattercake {

    protected String getDesc(){
        return "煎饼";
    }

    protected int getCost(){
        return 8;
    }
}
