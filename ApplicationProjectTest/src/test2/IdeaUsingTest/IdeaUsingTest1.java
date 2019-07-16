package test2.IdeaUsingTest;

public class IdeaUsingTest1 {
    public static void main(String[] args){
        int a=3;
        a=testAdd(a);
        int b=2;
        for(int i=0;i<100;i++){
            int n=i*10;
            int m=n+1;
            System.out.println(m);
        }

    }

    public static int testAdd(int a){
        return a+1;
    }
}
