package test1.ForEachTest;

/**
 * 跳出多重循环
 */
public class ForEachTest1 {
    public static void main(String args[]) {
        int arr[][] = { { 1, 2, 3 }, { 4, 5, 6, 7 }, { 8,9 } };
        boolean found = true;
        System.out.println("arr.length " + arr.length);
        for (int i = 0; i < arr.length && found; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                System.out.println("i=" + i + ",j=" + j);
                if (arr[i][j] == 5) {
                    found = false; // 修改了外层循环中的参数found
                    break; // 跳出循环
                }
            }
        }
    }
}
