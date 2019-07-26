package test1;

/**
 * Created by liaura_ljl on 2018/8/16.
 */
public class TryCatchTest {
    public static void main(String[] args){
        /*for(int i=1;i<10;i++){
            int a=1+1;
            int c=0;
            try{
                int b=a/c;
            }catch (Exception w){
                w.printStackTrace();
            }
            int d=0;
        }
        int d=0;*/
        try{
            //s1();
            //s2();
            s3();
        }catch (Exception e){
            int xx=0;
        }
    }

    public static boolean s1(){
        int a=0;
        int b=1;
        int c=b/a;
        return true;
    }

    public static boolean s2(){
        try{
            int a=0;
            int b=1;
            int c=b/a;
            return true;
        }catch (Exception e){
            int ss=0;
        }
        return false;
    }

    public static boolean s3()throws Exception {
        int a=0;
        int b=1;
        int c=b/a;
        return true;
    }
}
