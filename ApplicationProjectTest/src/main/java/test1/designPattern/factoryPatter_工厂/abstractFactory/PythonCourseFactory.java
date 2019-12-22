package test1.designPattern.factoryPatter_工厂.abstractFactory;

/**
 * Created by liaura_ljl on 2019/12/22.
 */
public class PythonCourseFactory implements CourseFactory {
    @Override
    public Video getVideo() {
        return new PythonVideo();
    }

    @Override
    public Article getArticle() {
        return new PythonArticle();
    }
}
