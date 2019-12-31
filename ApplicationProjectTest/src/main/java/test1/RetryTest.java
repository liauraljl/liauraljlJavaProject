package test1;

public class RetryTest {
    public static void main(String[] args) {
        new RetryTest().start();
    }

    private void start() {
        //test1();
        test2();
    }

    private void test1(){
        int a=0;
        retry:
        for(;;){
            for(;;){
                a++;
                if(a==10){
                    break;
                }
            }
        }
        //System.out.println(a);
    }

    private void test2(){
        int a=0;
        retry://跳出双重循环
        for(;;){
            for(;;){
                a++;
                if(a==10){
                    break retry;
                }
            }
        }
        System.out.println(a);
    }
}
