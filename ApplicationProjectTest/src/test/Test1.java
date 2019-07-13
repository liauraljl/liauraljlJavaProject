package test;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by liaura_ljl on 2018/4/3.
 */
public class Test1 {
    public static void main(String args[]) {
        new Test1().test1();
        ArrayList<Integer> arrayList = new ArrayList<>();

        System.out.println(getArrayListCapacity(arrayList));

        //增加元素，使其扩容
        arrayList.add(0);
        System.out.println(getArrayListCapacity(arrayList));

        for(int i = 0; i < 10; ++i)
            arrayList.add(0);
        System.out.println(getArrayListCapacity(arrayList));

        for(int i = 0; i < 5; ++i)
            arrayList.add(0);
        System.out.println(getArrayListCapacity(arrayList));
    }

    public static int getArrayListCapacity(ArrayList<?> arrayList) {
        Class<ArrayList> arrayListClass = ArrayList.class;
        try {
            Field field = arrayListClass.getDeclaredField("elementData");
            field.setAccessible(true);
            Object[] objects = (Object[]) field.get(arrayList);
            return objects.length;
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
            return -1;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return -1;
        }
    }

    private void test1(){
        List<Integer> lst=new ArrayList<Integer>(){
            @Override
            public String toString(){
                String str="";
                for(int i=0;i<this.size();i++){
                    str+=i+":="+this.get(i)+";\t";
                }
                return str;
            }
        };
        lst.add(5);
        String s1=lst.toString();
        lst.set(0,6);
        String s2=lst.toString();
        int a=0;
    }
}
