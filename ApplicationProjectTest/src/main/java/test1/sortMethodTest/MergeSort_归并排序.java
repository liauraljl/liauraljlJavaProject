package test1.sortMethodTest;

/**
 * Created by liaura_ljl on 2019/4/9.
 * 分而治之(divide - conquer);每个递归过程涉及三个步骤
 第一, 分解: 把待排序的 n 个元素的序列分解成两个子序列, 每个子序列包括 n/2 个元素.
 第二, 治理: 对每个子序列分别调用归并排序MergeSort, 进行递归操作
 第三, 合并: 合并两个排好序的子序列,生成排序结果.
 */
public class MergeSort_归并排序 {
    public static int[] sort(int[] a,int low,int high){
        int mid = (low+high)/2;
        if(low<high){
            sort(a,low,mid);
            sort(a,mid+1,high);
            //左右归并
            merge(a,low,mid,high);
        }
        return a;
    }

    public static void merge(int[] a, int low, int mid, int high) {
        int[] temp = new int[high-low+1];
        int i= low;
        int j = mid+1;
        int k=0;
        // 把较小的数先移到新数组中
        while(i<=mid && j<=high){
            if(a[i]<a[j]){
                temp[k++] = a[i++];
            }else{
                temp[k++] = a[j++];
            }
        }
        // 把左边剩余的数移入数组
        while(i<=mid){
            temp[k++] = a[i++];
        }
        // 把右边边剩余的数移入数组
        while(j<=high){
            temp[k++] = a[j++];
        }
        // 把新数组中的数覆盖nums数组
        for(int x=0;x<temp.length;x++){
            a[x+low] = temp[x];
        }
    }

    public static void main(String[] args){
        int arr[]={2,1,4,3,2,9,5,6,8,7};
        int[] newArr=sort(arr,0,arr.length-1);
        for(int temp:newArr){
            System.out.print(temp+",");
        }
    }
}
