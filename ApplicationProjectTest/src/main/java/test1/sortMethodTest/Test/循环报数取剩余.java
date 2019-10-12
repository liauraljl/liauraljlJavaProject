package test1.sortMethodTest.Test;

/**
 * Created by liaura_ljl on 2019/4/9.
 */
public class 循环报数取剩余 {

    public static void main(String[] args) {
        test();
        int num = 10;
        boolean[] array = new boolean[num];
        for (int i = 0; i < num; i++) {
            array[i] = true;
        }
        int index = 0;
        int count = 0;
        int n = num;
        while (n > 1) {
            if (array[index] == true) {
                count++;
                if (count == 3)
// 当count等于3时，就淘汰一个；
                {
                    array[index] = false;
                    n--; // 当有一个被淘汰时，n--；
                    count = 0;
                }
            }
            index++;
// 当从0循环到29时，重新置index为0；
            if (index == num-1) {
                index = 0;
            }
        }
        for (int i = 0; i < num; i++) {
            if (array[i] == true)
                System.out.println(i + 1);
        }
    }

    public static void test(){
        int num=10;
        boolean[] arr=new boolean[num];
        for(int i=0;i<arr.length;i++){
            arr[i]=true;
        }
        int n=num;
        int count=0;
        int index=0;
        while (n>1){
            if(arr[index]==true){
                count++;
                if(count==3){
                    arr[index]=false;
                    count=0;
                    n--;
                }
            }
            index++;
            if(index==num-1){
                index=0;
            }
        }
        for(int i=0;i<num;i++){
            if(arr[i]==true){
                System.out.print(i+1);
            }
        }
    }
}
