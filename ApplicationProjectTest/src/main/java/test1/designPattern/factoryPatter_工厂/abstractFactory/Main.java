package test1.designPattern.factoryPatter_工厂.abstractFactory;

/**
 * 抽象工厂模式
 * Created by liaura_ljl on 2019/12/22.
 */
public class Main {
    public static void main(String[] args) {
        JavaCourseFactory javaCourseFactory=new JavaCourseFactory();
        Video javaVideo=javaCourseFactory.getVideo();
        javaVideo.playVideo();
        Article javaArticle=javaCourseFactory.getArticle();
        javaArticle.readArticle();


        PythonCourseFactory pythonCourseFactory=new PythonCourseFactory();
        pythonCourseFactory.getArticle().readArticle();
        pythonCourseFactory.getVideo().playVideo();
    }
}
