package test1.stringTest;

import java.util.Arrays;

/**
 * Created by liaura_ljl on 2018/5/22.
 */
public class StringArrSplitTest {

    public static void main(String[] args){
        String value="a,bb,ccc";
        if(value.contains(",")){
            System.err.print("1");
            String[] userArr=value.split(",");
            if(Arrays.asList(userArr).contains("bb")){
                System.out.println("bb");
            }else{
                System.out.print("c");
            }
        }
    }
}
