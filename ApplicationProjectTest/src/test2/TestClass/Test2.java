package test2.TestClass;

public class Test2 {
    public static void main(String[] args){
        int[] arr={1,2,9,8,7,3,6};
        boolean changed=true;
        do{
            changed=false;
            for(int j=0;j<arr.length-1;j++){
                if(arr[j]>arr[j+1]){
                    int temp=arr[j];
                    arr[j]=arr[j+1];
                    arr[j+1]=temp;
                    changed=true;
                }
            }
        }while (changed);
        for(int t:arr){
            System.out.print(t+" ");
        }
    }
}
