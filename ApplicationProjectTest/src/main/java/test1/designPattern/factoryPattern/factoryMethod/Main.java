package test1.designPattern.factoryPattern.factoryMethod;

/**
 * 工厂方法模式
 * Created by liaura_ljl on 2019/12/22.
 */
public class Main {
    public static void main(String[] args) {
        JavaVideoFactory javaVideoFactory=new JavaVideoFactory();
        javaVideoFactory.getVideo().playVideo();

        PythonVideoFactory pythonVideoFactory=new PythonVideoFactory();
        pythonVideoFactory.getVideo().playVideo();
    }
}
