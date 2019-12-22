package test1.designPattern.decorator_装饰者.v2;

public abstract class AbstractDecorator extends ABattercake {

    /**
     * 要求子类必须实现某方法时才用抽象类 此处定义抽象方法
     */
    /*protected abstract void doSomething();*/
    private ABattercake aBattercake;

    public AbstractDecorator(ABattercake aBattercake){
        this.aBattercake=aBattercake;
    }
    @Override
    protected String getDesc() {
        return aBattercake.getDesc();
    }

    @Override
    protected int getCost() {
        return aBattercake.getCost();
    }
}
