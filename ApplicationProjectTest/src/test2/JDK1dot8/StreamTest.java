package test2.JDK1dot8;

import Test.TestClass.WeChatInfo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class StreamTest {

    private static int a=0;

    private static void dd(){

    }

    public static void main(String[] args) {
        StreamTest ss=new StreamTest();
        ss.a++;
        int cc=ss.a;
        new StreamTest().start();
    }

    private void start(){
        test1();

        new StreamTest().dd();
        int cc= new StreamTest().a;
        ///test1();
        int a=9;
        Long b= Long.valueOf(a);
        //Long.parseLong();
        test2();
    }

    private void test1(){
        List<WeChatInfo> weChatInfos=new ArrayList<WeChatInfo>();
        for(int i=1;i<3100000;i++){
            weChatInfos.add(new WeChatInfo("weChatId"+i,"weChatAccount"+i,"weChatNickName"+i,5*i));
        }
        boolean check1=weChatInfos.stream().anyMatch(t->t.getSortId().equals(15));
        boolean chk2=weChatInfos.stream().noneMatch(t->t.getSortId().equals(26));
        /*WeChatInfo weChatInfoFirst=weChatInfos.stream().findFirst().get();
        long count=weChatInfos.stream().count();
        List<Integer> sortLst=weChatInfos.stream().map(WeChatInfo::getSortId).collect(Collectors.toList());
        List<Integer> sortLst2=weChatInfos.parallelStream()
                .filter(t -> (t.getSortId()%10)==5)
                .sorted(Comparator.comparing(WeChatInfo::getSortId).reversed())
                .map(WeChatInfo::getSortId)
                .collect(Collectors.toList());
        List<Integer> sortLst3=weChatInfos.stream()
                .filter(t -> (t.getSortId()%10)==5)
                .sorted(Comparator.comparing(WeChatInfo::getSortId))
                .map(WeChatInfo::getSortId)
                .collect(Collectors.toList());
        weChatInfos.forEach(t->System.out.println(t.toString()));
        weChatInfos.stream().forEach(t->System.out.println(t.toString()));*/
        int aa=0;
        while (aa==0){
            long a=System.currentTimeMillis();
            for(int i=0;i<20;i++){
                Collections.sort(weChatInfos, new Comparator<WeChatInfo>() {
                    @Override
                    public int compare(WeChatInfo o1, WeChatInfo o2) {
                        return  o2.getSortId().compareTo(o1.getSortId());
                    }
                });//-------------------------a1
            }
            long b=System.currentTimeMillis();
            for(int i=0;i<20;i++){
                weChatInfos.sort(Comparator.comparing(WeChatInfo::getSortId).reversed());//---------a2
            }
            long c=System.currentTimeMillis();
            for(int i=0;i<20;i++){
                Collections.sort(weChatInfos,Comparator.comparing(WeChatInfo::getSortId).reversed());//--a3
            }
            long d=System.currentTimeMillis();
            for(int i=0;i<20;i++){
                Collections.sort(weChatInfos, (o1, o2) -> o2.getSortId().compareTo(o1.getSortId()));//---a4
            }
            long e=System.currentTimeMillis();
            long a1=b-a;
            long a2=c-b;
            long a3=d-c;
            long a4=e-d;
            int bbb=0;
        }
        //WeChatInfo weChatInfo33333333=weChatInfos.stream().findAny().filter(t->t.getWeChatId().equals("weChatId13")).get();
        int axx=0;

    }

    private void test2(){
        Object obj=new Object();
        obj.equals("");
        obj.hashCode();

        int a=5456;
        Long b=new Long(a);
        System.out.println(b);
    }

}
