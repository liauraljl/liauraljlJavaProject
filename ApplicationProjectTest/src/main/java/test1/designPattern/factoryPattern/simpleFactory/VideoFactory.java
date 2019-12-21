package test1.designPattern.factoryPattern.simpleFactory;

/**
 * Created by liaura_ljl on 2019/12/22.
 */
public class VideoFactory {
    public static Video getInstance(String type){
        if("java".equals(type)){
            return new JavaVideo();
        }
        if("python".equals(type)){
            return new PythonVideo();
        }
        return null;
    }

    public static Video getInstance(Class c){
        Video video=null;
        try {
            video=(Video)Class.forName(c.getName()).newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return video;
    }
}
