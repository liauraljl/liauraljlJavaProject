package test.setAndGetTest;

/**
 * Created by liaura_ljl on 2018/1/18.
 */
public class Test1 {
    public static void main(String[] args){
        Dog dog1=new Dog();
        dog1.setId(1);
        dog1.setName("狗1");
        Dog dog2=new Dog();
        dog2.setId(2);
        dog2.setName(dog1.getName());
        dog1.setName("我是1");
        System.out.print("1:"+dog1.getName()+",2:"+dog2.getName());
    }
}
