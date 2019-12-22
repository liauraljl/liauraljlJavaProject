package test1.designPattern.decorator_装饰者.v2;

public class SausageDecorator extends AbstractDecorator {
    public SausageDecorator(ABattercake aBattercake) {
        super(aBattercake);
    }

    @Override
    protected String getDesc() {
        return super.getDesc()+" 加一根香肠";
    }

    @Override
    protected int getCost() {
        return super.getCost()+2;
    }
}
