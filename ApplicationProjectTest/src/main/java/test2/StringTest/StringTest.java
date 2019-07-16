package test2.StringTest;

public class StringTest {
    private static int xx=100;
    public static void main(String[] args) {
        test2();
        test();
        Integer a22=11;
        int aInt=11;
        System.out.println(a22==aInt);

        String aa="22237038880@chatroom";
        String dd="ss";
        boolean a1=aa.contains("@chatroom");
        boolean a2=dd.contains("@chatroom");
        new StringTest().xx++;
        String a="aaa";
        String b=new String(a);
        String c=new String("aaa");
        System.out.println(a==b);
        System.out.println(a==c);
        System.out.println(b==c);

        int x=0;int y=3;
        switch (y){
            case 1:x++;
            case 2:x++;x++;
            case 3:x++;x++;x++;
            case 4:x+=4;
        }
        System.out.println(x);
    }

    private static void test(){
        String ss="guy1270864615:\n我的";
        int a=ss.indexOf(":");
        String b=ss.substring(0,a);
        String c=ss.substring(15);
        System.out.println("");
    }

    public static void test2(){
        String fo="dss:ewee:%s:%s";
        String ss=String.format(fo,"wechatid","msgId");
        System.out.println(ss);
    }
}
