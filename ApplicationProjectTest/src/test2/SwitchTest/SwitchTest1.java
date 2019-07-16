package test2.SwitchTest;

public class SwitchTest1 {
    public static void main(String[] args) {
        new SwitchTest1().start();
    }
    private void start(){
        int a=1;

        int b=0;
        int c=0;
        switch (a){
            case 1:
                b=1;
            case 2:
                b=2;
            case 3:
                b=3;
            c=222;
            case 4:
                b=4;
                c=33;
                break;
            case 5:
                b=5;
                c=44;
                break;
            default:
                break;
        }
        System.out.println(a+","+b+","+c);




    }
}
