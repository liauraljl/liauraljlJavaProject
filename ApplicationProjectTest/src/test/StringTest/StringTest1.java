package test.StringTest;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by liaura_ljl on 2018/1/24.
 */
public class StringTest1 {
    public static void main(String[] args){
        /*String str1="fdgfdgg冯大哥[共享]";
        int length=str1.length();
        int start=str1.indexOf("[fsd]");
        int start2=str1.indexOf("[共享]");
        int start3=str1.indexOf("f");
        int a=1;*/
        //test3();
        String size=getDataSize(1999);
        System.out.print(size);
    }

    public static void test2() {
        List s = new ArrayList<String>();
        //s.add
    }

    public static void test3(){
        int a=5;
        int b=10;
        int c=11;
        int d=15;
        int m=b%a;
        int n=c%a;
        int q=d%a;
        int p=0;
    }

    public static String getDataSize(long size) {
        DecimalFormat formater = new DecimalFormat("####.00");
        if (size < 1024) {
            return size + "bytes";
        } else if (size < 1024 * 1024) {
            float kbsize = size / 1024f;
            return formater.format(kbsize) + "KB";
        } else if (size < 1024 * 1024 * 1024) {
            float mbsize = size / 1024f / 1024f;
            return formater.format(mbsize) + "MB";
        } else if (size < 1024 * 1024 * 1024 * 1024) {
            float gbsize = size / 1024f / 1024f / 1024f;
            return formater.format(gbsize) + "GB";
        } else {
            return "size: error";
        }
    }

}
