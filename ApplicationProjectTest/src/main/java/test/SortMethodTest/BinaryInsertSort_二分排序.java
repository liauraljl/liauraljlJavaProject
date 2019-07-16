package test.SortMethodTest;

/**
 * Created by liaura_ljl on 2019/4/9.
 */
public class BinaryInsertSort_二分排序 {
    /**
     * 二分法排序<br>
     * 根据排序原则，每次我们都是在一个有序序列中插入一个新的数字<br>
     * 那么我们可以将这个有序序列进行二分。<br>
     * 左游标left为0，右游标right为i-1(i是这个数字在原数组中的位置)<br>
     * middle初始为。<br>
     * 当left<=right时<br>
     * middle是left和right的中值。<br>
     * 我们作如下操作。如果array[i]的值比array[middle]值大。<br>
     * 那么我们就移动左游标令值为middle+1<br>
     * 负责就移动右游标为middle-1<br>
     * 移动完成后,我们需要将i-1到left之间的值进行依次向后移动给array[i]空出一个位置然后将array[i]插入
     * <p style="color:red">时间复杂度n</p>
     */
    public static int[] binaryInsertSort(int[] array){
        for(int i = 0;i<array.length;i++){
            int temp = array[i];//待插入到前面有序序列的值
            int left = 0;//有序序列的左侧
            int right = i-1;//有序序列的右侧
            int middle = 0;//有序序列的中间
            while(left <= right){
                middle = (left + right)/2;//赋值
                if(temp<array[middle]){
                    right = middle-1;
                }else{
                    left = middle + 1;
                }
            }
            for(int j = i-1;j>=left;j--){
                //从i-1到left依次向后移动一位,等待temp值插入
                array[j+1] = array[j];
            }
            if(left != i ){
                array[left] = temp;
            }
        }
        return array;
    }

    public static void main(String[] args){
        int arr[]={2,1,4,3,2,9,5,6,8,7};
        int newArr[]=binaryInsertSort(arr);
        for(int temp:newArr){
            System.out.print(temp+",");
        }
    }
}
