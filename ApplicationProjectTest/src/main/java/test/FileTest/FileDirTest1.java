package test.FileTest;

import java.io.File;

/**
 * Created by liaura_ljl on 2018/4/13.
 */
public class FileDirTest1 {
    public static void main(String[] args){
        String filePath="E:\\A";
        checkDir(filePath);
    }

    private static boolean checkDir(String filePath){
        File file=new File(filePath);
        if(file.exists()){
            int a=0;
        }else{
            file.mkdir();
        }
        if(file.isDirectory()){
            int b=0;
        }
        return true;
    }
}
