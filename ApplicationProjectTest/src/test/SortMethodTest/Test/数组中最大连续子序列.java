package test.SortMethodTest.Test;

/**
 * Created by liaura_ljl on 2019/4/9.
 */
public class 数组中最大连续子序列 {
    public static void maxSum(int[] nums) {
        int start = 0;
        int end = 0;
        int max = Integer.MIN_VALUE;

        int sum = 0;
        int ts = 0;
        for(int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if(sum > max) {
                start = ts;
                end = i;
                max = sum;

            }
            if(sum < 0) {
                ts = i + 1;
                sum = 0;
            }
        }

        System.out.println("maxSum = " + max + ", start : " + start + ", end = " + end);
    }

    public static void test4(int[] array) {
        int end=0;
        int start=0;
        int max = Integer.MIN_VALUE;

        int sum = 0; //子串和
        int ts=0;

        for (int i = 0; i < array.length; i++) {
            sum += array[i];
            if(sum > max) {
                max = sum;
                end=i;
                start=ts;
            }
            if(sum < 0) {
                sum = 0;
                ts=i+1;
            }
        }
        System.out.println("maxSum = " + max + ", start : " + start + ", end = " + end);
    }

    public static void main(String[] args){
      int[] arr1={1,3,-1,3,4};
      int[] arr2={-1,-2,-3,-4};
      maxSum(arr1);
      maxSum(arr2);
      test4(arr1);
      test4(arr2);
    }
}
