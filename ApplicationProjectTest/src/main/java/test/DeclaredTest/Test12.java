package test.DeclaredTest;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;

/**
 * Created by liaura_ljl on 2018/11/8.
 */
public class Test12 {
    private String name;
    private Integer age;

    public Test12(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public Test12() {
    }

    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException, IOException {
        new Test12().test();
        Test12 tt = new Test12("丽丽", 21);
        //给你一个属性，比如name，来获取name的值
        String key = "name";
        Field field = tt.getClass().getDeclaredField(key);
        Object obj=field.get(tt);
        System.out.print("name:"+obj==null?"":obj.toString());

    }

    private void test() throws IOException {
        File file=new File("E:\\DistWork\\App\\tzgh_app.rar");
        String parent=file.getParent();
        String dd=file.getAbsolutePath();
        String ss=file.getCanonicalPath();
        String sss=file.getPath();
        String sssss=file.getName();
        int a0=0;
    }
}
