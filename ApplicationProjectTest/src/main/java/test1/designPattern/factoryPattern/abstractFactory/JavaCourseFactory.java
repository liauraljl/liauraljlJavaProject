package test1.designPattern.factoryPattern.abstractFactory;

/**
 * Created by liaura_ljl on 2019/12/22.
 */
public class JavaCourseFactory implements CourseFactory {
    @Override
    public Video getVideo() {
        return new JavaVideo();
    }

    @Override
    public Article getArticle() {
        return new JavaArticle();
    }
}
