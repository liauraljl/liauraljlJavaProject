package test1.setTest;

import com.alibaba.fastjson.JSON;
import lombok.Data;

import java.util.*;

public class SetDeleteTest {
    public static void main(String[] args) {
        new SetDeleteTest().start();
    }

    private void start(){
        //deleteTest();
        test1();
    }

    private void deleteTest(){
        List<Integer> set=new ArrayList<>();
        for(int i=0;i<10;i++){
            set.add(i);
        }
        Iterator<Integer> iterator=set.iterator();
        while (iterator.hasNext()){
            Integer item=iterator.next();
            if(item.equals(3)){
                System.out.println("remove:"+item);
                iterator.remove();
            }else {
                System.out.println(item);

            }
        }
        for(Integer item:set){
            System.out.println(item);
        }
    }

    private void test1(){
        Set<String> set=new HashSet<>();
        for(int i=0;i<10;i++){
            set.add(i+"");
        }
        User user=new User();
        user.setIds(new ArrayList(set));
        System.out.println(JSON.toJSONString(user));
    }

    @Data
    private class User{
        List<Long> ids;
    }
}
