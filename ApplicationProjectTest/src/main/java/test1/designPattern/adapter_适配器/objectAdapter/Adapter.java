package test1.designPattern.adapter_适配器.objectAdapter;

public class Adapter implements Target {

    private Adaptee adaptee=new Adaptee();
    @Override
    public void request() {
        adaptee.adapteeRequest();
    }
}
