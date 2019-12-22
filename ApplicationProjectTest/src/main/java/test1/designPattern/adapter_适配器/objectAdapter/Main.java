package test1.designPattern.adapter_适配器.objectAdapter;


/**
 * 对象适配器
 */
public class Main {
    public static void main(String[] args) {
        Target target=new ConcreteTarget();
        target.request();

        Target adapterTarget=new Adapter();
        adapterTarget.request();
    }
}
