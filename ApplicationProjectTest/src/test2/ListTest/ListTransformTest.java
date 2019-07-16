package test2.ListTest;

import Test.TestClass.GafeCat;
import com.google.common.base.Function;
import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.List;

public class ListTransformTest {
    public static void main(String[] args){
        /*GafeCat gafeCat=new GafeCat();*/
        List<GafeCat> gafeCatList=new ArrayList<>();
        for(int i=0;i<10;i++){
            /*GafeCat gafeCat=new GafeCat("name"+(i+1),i+1,"color"+(i+1));
            gafeCatList.add(gafeCat);*/
        }
        List<Integer> noList= Lists.transform(gafeCatList, new Function<GafeCat, Integer>() {
            @Override
            public Integer apply(GafeCat gafeCat) {
                return gafeCat.getNo();
            }
        });
        noList.forEach(item->System.out.println(item));
    }
}
