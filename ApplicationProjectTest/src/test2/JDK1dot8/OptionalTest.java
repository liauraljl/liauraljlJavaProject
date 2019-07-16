package test2.JDK1dot8;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OptionalTest {
    public static void main(String[] args) {
        new OptionalTest().start();
    }

    private void start(){
        String name=null;
        List<String> names=new ArrayList<>();
        names.add("1");
        names.add("2");
        if(names!=null&&names.size()>0){
            name=names.get(0);
        }
        name= Optional.ofNullable(names).map(list->list.get(1)).orElse("s");
        System.out.println("");
    }
}
