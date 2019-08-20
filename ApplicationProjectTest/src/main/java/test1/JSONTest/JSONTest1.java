package test1.JSONTest;

import com.alibaba.fastjson.JSON;
import test1.setAndGetTest.Cat;

public class JSONTest1 {
    public static void main(String[] args) {
        String jsonStr="{\"id\":1,\"name\":\"name\",\"age\":5}";
        Cat cat=JSON.parseObject(jsonStr, Cat.class);
        System.out.println(JSON.toJSONString(cat));
    }
}
