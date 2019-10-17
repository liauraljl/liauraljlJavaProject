package test1.threadTest.pStreamTest;

public class NoPStream {
    public static void main(String[] args) {
        long a=System.currentTimeMillis();
        for(int i=0;i<=1000;i++){
            for(int j=0;j<=1000;j++){
                System.out.println((i+j)*i/2);
            }
        }
        long b=System.currentTimeMillis();
        System.out.println((b-a)/1000+"s");
    }
}
