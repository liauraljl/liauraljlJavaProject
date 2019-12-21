package test1.designPattern.factoryPattern.factoryMethod;

/**
 * Created by liaura_ljl on 2019/12/22.
 */
public class PythonVideoFactory extends VideoFactory {
    @Override
    public Video getVideo() {
        return new PythonVideo();
    }
}
