package test1.threadTest.pStreamTest;

/**
 * 并行计算模式
 */
public class PStreamMain {
    public static void main(String[] args) {
        long a=System.currentTimeMillis();
        Div.a=a;
        new Thread(new Plus()).start();
        new Thread(new Multiply()).start();
        new Thread(new Div()).start();

        for(int i=0;i<=1000;i++){
            for(int j=0;j<=1000;j++){
                Msg msg=new Msg();
                msg.i=i;
                msg.j=j;
                msg.orgStr="(("+i+"+"+j+")*"+i+")/2";
                Plus.bq.add(msg);
            }
        }
    }
}
