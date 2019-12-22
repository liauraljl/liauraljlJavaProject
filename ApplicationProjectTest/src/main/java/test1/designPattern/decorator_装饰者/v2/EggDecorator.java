package test1.designPattern.decorator_装饰者.v2;

public class EggDecorator extends AbstractDecorator{
    public EggDecorator(ABattercake aBattercake) {
        super(aBattercake);
    }

    @Override
    protected String getDesc() {
        return super.getDesc()+" 加一个鸡蛋";
    }

    @Override
    protected int getCost() {
        return super.getCost()+1;
    }
}
