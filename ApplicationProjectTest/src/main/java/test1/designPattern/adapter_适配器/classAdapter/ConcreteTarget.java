package test1.designPattern.adapter_适配器.classAdapter;

public class ConcreteTarget implements Target {
    @Override
    public void request() {
        System.out.println("ConcreteTarget 目标方法");
    }
}
