package test1.designPattern.factoryPattern.simpleFactory;

/**
 * 简单工厂模式
 * Created by liaura_ljl on 2019/12/22.
 */
public class Main {
    public static void main(String[] args) {
        Video video= VideoFactory.getInstance("java");
        video.playVideo();

        Video video2= VideoFactory.getInstance(PythonVideo.class);
        video2.playVideo();
    }
}
