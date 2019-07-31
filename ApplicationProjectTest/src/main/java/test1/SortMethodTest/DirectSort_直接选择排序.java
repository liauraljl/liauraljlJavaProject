package test1.SortMethodTest;

/**
 * Created by liaura_ljl on 2019/4/9.
 */
public class DirectSort_直接选择排序 {
    public static int[] directSort(int[] array) {
        int len = array.length;
        for (int i = 0; i < len - 1; i++) {
            int min = i;// 设当前第i条记录为最小值
            // 找出(i+1)到(len-1)中最小值的下标
            for (int j = i + 1; j < len; j++) {
                if (array[j] < array[min]) {
                    min = j;// 记录最小值下标
                }
            }
            // 将最小值记录与第i条记录交换
            if (min != i) {
                int temp = array[i];
                array[i] = array[min];
                array[min] = temp;
            }
        }
        return array;
    }

    public static void main(String[] args){
        int arr[]={2,1,4,3,2,9,5,6,8,7};
        int[] newArr=directSort(arr);
        for(int temp:newArr){
            System.out.print(temp+",");
        }
    }

}
