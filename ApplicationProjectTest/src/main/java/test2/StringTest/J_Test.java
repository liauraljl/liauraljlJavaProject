package test2.StringTest;

public class J_Test {

    public static void mb(StringBuffer x,StringBuffer y){
        x.append(y);
        y=x;
    }

    public static void main(String[] args) {
        int[] arr=new int[121];

        StringBuffer a=new StringBuffer("A");
        StringBuffer b=new StringBuffer("B");
        mb(a,b);
        mb2(a,b,null);
        System.out.println(a+","+b);
    }

    public static void mb2(StringBuffer a,StringBuffer b,StringBuffer c){
        a=b;
        b=a.append("x");
        //c.append("x");
        a.append("x");
        /*a.append(a);*/
        //String
        //Integer

        int i=0;
        Math.random();
        int cc=(int)20.5;
        int ccc=(int)20.49;
        int d=-0;
    }

    public static void main3(StringBuffer x,StringBuffer y){
        x=y;
        y=x.append(y);
    }
}
