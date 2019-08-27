package test1.FileTest;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.compress.archivers.tar.TarArchiveEntry;
import org.apache.commons.compress.archivers.tar.TarArchiveInputStream;
import org.apache.commons.compress.archivers.tar.TarArchiveOutputStream;
import org.apache.commons.compress.utils.IOUtils;
import org.apache.commons.io.FileUtils;

import java.io.*;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class GZIPUtil {

    private static String defaultPath;
    private static Map<String,File> md5ToFileMap;

    static{
        md5ToFileMap = new HashMap<>();
        String os = System.getProperty("os.name").toLowerCase();
        if(os.startsWith("win")){
            defaultPath = "D:\\";
        }else{
            defaultPath = "/opt";
        }
    }

    public static File createZhuJiFansTxt(String fileName, Map<Integer, List<String>> map){
        File target = new File(GZIPUtil.defaultPath+fileName+".txt");
        FileOutputStream fileOutputStream = null;
        try{
            if(!target.exists()){
                target.createNewFile();
            }
            fileOutputStream = new FileOutputStream(target);
            for(Map.Entry<Integer,List<String>> entry:map.entrySet()) {
                List<String> value = entry.getValue();
                for(String str:value){
                    fileOutputStream.write(entry.getKey().toString().getBytes());
                    fileOutputStream.write(('\t'+str+'\n').getBytes());
                }
            }
            fileOutputStream.flush();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(fileOutputStream != null){
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return target;
    }

    public static File pack(File[] sources,String fileName) {
        File target = new File(GZIPUtil.defaultPath);
        if(!target.exists()){
            target.mkdirs();
        }
        String targetPath = null;
        if(fileName != null && !"".equals(fileName)){
            targetPath = target.getPath()+System.getProperty("file.separator")+fileName+".tar";
        }else{
            targetPath = target.getPath()+System.getProperty("file.separator")+new IdWorker().nextId()+".tar";
        }
        FileOutputStream out = null;
        File outTargeFile = new File(targetPath);
        try {
            outTargeFile.createNewFile();
            out = new FileOutputStream(outTargeFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
        TarArchiveOutputStream os = new TarArchiveOutputStream(out);
        FileInputStream fileInputStream = null;
        for (File file : sources) {
            try {
                fileInputStream = new FileInputStream(file);
                TarArchiveEntry tarArchiveEntry = new TarArchiveEntry(file);
                tarArchiveEntry.setSize(file.length());
                os.putArchiveEntry(tarArchiveEntry);
                IOUtils.copy(fileInputStream, os);
                os.flush();
                os.closeArchiveEntry();
                fileInputStream.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if(os != null) {
            try {
                os.flush();
                os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if(out != null) {
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return outTargeFile;
    }

    public static File compress(File source) {
        File target = new File(source.getPath() + ".gz");
        try {
            target.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        FileInputStream in = null;
        GZIPOutputStream out = null;
        try {
            in = new FileInputStream(source);
            out = new GZIPOutputStream(new FileOutputStream(target));
            byte[] array = new byte[1024];
            int number = -1;
            while((number = in.read(array, 0, array.length)) != -1) {
                out.write(array, 0, number);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            if(in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    return null;
                }
            }
            if(out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    return null;
                }
            }
        }
        return target;
    }

    public static void createDirectory(String outputDir,String subDir){
        File file = new File(outputDir);
        if(!(subDir == null || subDir.trim().equals(""))){//子目录不为空
            file = new File(outputDir + System.getProperty("file.separator") + subDir);
        }
        if(!file.exists()){
            if(!file.getParentFile().exists())
                file.getParentFile().mkdirs();
            file.mkdirs();
        }
    }


    public static void unCsvGz(File file, String outputDir) throws IOException{
        try{
            createDirectory(outputDir,null);
            FileInputStream fis = new FileInputStream(file);
            FileOutputStream out=null;
            GZIPInputStream gis = new GZIPInputStream(fis);
            try{
                out = new FileOutputStream(outputDir+System.getProperty("file.separator")+file.getName().replace(".gz",""));
                int count;
                byte data[] = new byte[2048];
                while ((count = gis.read(data)) != -1) {
                    out.write(data, 0, count);
                }
            }catch (IOException e){

            }finally {
                if(gis!=null){
                    gis.close();
                }
                if(fis!=null){
                    fis.close();
                }
                if(out!=null){
                    out.flush();
                    out.close();
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }



        /*TarArchiveInputStream tarIn = null;
        try{
            tarIn = new TarArchiveInputStream(new GZIPInputStream(
                    new BufferedInputStream(new FileInputStream(file)),
                    1024 * 2));

            createDirectory(outputDir,null);//创建输出目录
            TarArchiveEntry entry = null;
            while((entry = tarIn.getNextTarEntry()) != null ){
                if(entry.isDirectory()){//是目录
                    entry.getName();
                    createDirectory(outputDir,entry.getName());//创建空目录
                }else{//是文件
                    File tmpFile = new File(outputDir+System.getProperty("file.separator")+ entry.getName());
                    createDirectory(tmpFile.getParent() +System.getProperty("file.separator"),null);//创建输出目录
                    OutputStream out = null;
                    try{
                        out = new FileOutputStream(tmpFile);
                        int length = 0;

                        byte[] b = new byte[2048];

                        while((length = tarIn.read(b)) != -1){
                            out.write(b, 0, length);
                        }

                    }catch(IOException ex){
                        throw ex;
                    }finally{

                        if(out!=null)
                            out.close();
                    }
                }
            }
        }catch(IOException ex){
            throw new IOException("解压归档文件出现异常",ex);
        } finally{
            try{
                if(tarIn != null){
                    tarIn.close();
                }
            }catch(IOException ex){
                throw new IOException("关闭tarFile出现异常",ex);
            }
        }*/
    }

   /* public static void getAllFile(File parentPath, List<File> fileList, DealFileCallback<File> fileCallback, DealDirectoriesCallBack<File> directoriesCallBack){
        if(parentPath.isFile()) {
            fileList.add(parentPath);
            if(fileCallback != null){
                fileCallback.callBack(parentPath);
            }
            return;
        }else{
            for(File f:parentPath.listFiles()){
                getAllFile(f,fileList,fileCallback,directoriesCallBack);
            }
            if(parentPath.listFiles() == null || parentPath.listFiles().length <= 0){
                if(directoriesCallBack != null){
                    directoriesCallBack.callBack(parentPath);
                }
                return;
            }
        }
    }*/

    public static File downloadFileByUrl(String urlStr,String suffix){
        File file;
        if(suffix == null || "".equals(suffix) ){
            file = new File(defaultPath+System.getProperty("file.separator")+new IdWorker().nextId()+".html");
        }else{
            file = new File(defaultPath+System.getProperty("file.separator")+new IdWorker().nextId()+suffix);
        }
        try {
            file.createNewFile();
            FileUtils.copyURLToFile(new URL(urlStr),file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }

    /*public static boolean  getZhuJiActivityResult(String urlStr, DealFileCallback<File> fileCallback, DealDirectoriesCallBack<File> directoriesCallBack){
        boolean flag = false;
        File downloadFile = new File(defaultPath+System.getProperty("file.separator")+new IdWorker().nextId());
        File file = downloadFileByUrl(urlStr,".tar.gz");
        try{
            unTarGz(file,downloadFile.getPath());
            getAllFile(downloadFile,new ArrayList<>(),fileCallback,directoriesCallBack);
            flag = true;
        }catch (Exception e){
            System.out.printf("下载解压文件报错!");
        }finally {
            file.delete();
            downloadFile.delete();
        }
        return flag;
    }*/

    public static String getZhuJiFansMd5(String textName,String compressName, Map<Integer, List<String>> map){
        File fansFile = createZhuJiFansTxt(textName, map);
        File[] fansFiles = new File[]{fansFile};
        File packFile = pack(fansFiles,compressName);
        File compress = compress(packFile);
        String md5 = null;
        try {
            fansFile.delete();
            packFile.delete();
            md5 = DigestUtils.md5Hex(new FileInputStream(compress.getPath()));
            md5ToFileMap.put(md5,compress);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return md5;
    }

    public static void getAndHandleAllFile(File parentPath,DealFileCallback<File> fileCallback){
        if(parentPath.isFile()) {
            if(fileCallback != null){
                fileCallback.callBack(parentPath);
            }
            return;
        }else{
            for(File f:parentPath.listFiles()){
                getAndHandleAllFile(f,fileCallback);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        /*List<String> openId = new ArrayList<>();
        openId.add("ssssss");
        openId.add("sssssa");
        openId.add("sssssb");
        openId.add("sssssv");
        openId.add("ssssse");
        openId.add("sssssu");
        openId.add("sssssz");
        Map<Integer,List<String>> ids = new HashMap<>();
        ids.put(1,openId);
        String zhuJiFansMd5 = getZhuJiFansMd5("aaa_2019_07_24", "bbb__2019_07_24", ids);
        System.out.println(zhuJiFansMd5+":"+md5ToFileMap.get(zhuJiFansMd5).getName());
        unTarGz(md5ToFileMap.get(zhuJiFansMd5),"D:\\demodemodemodemo");*/
        /*boolean flag = getZhuJiActivityResult("http://zlib.net/zlib-1.2.11.tar.gz",f->{f.delete();},d->{d.delete();});
        System.out.println("sdsds:"+flag);*/
        new GZIPUtil().test3();
    }

    private void test1(){
        String urlStr="http://vodcq1251659802-10022853.cos.ap-chongqing.myqcloud.com/vodcq1251659802/1/playstat/20190821/1300029038_20190821.csv.gz?q-sign-algorithm=sha1&q-ak=AKIDIWe7AtI10PQkm8REDl4UO7I6myn6NDF7&q-sign-time=1566529628;1566572828&q-key-time=1566529628;1566572828&q-header-list=&q-url-param-list=&q-signature=8d04ef2d5c133ec06954aa2bb5ae2b5726ca404b";
        File unGzFileDir = new File(defaultPath+System.getProperty("file.separator")+new IdWorker().nextId());
        File gzFile = downloadFileByUrl(urlStr,".csv.gz");
        try{
            unCsvGz(gzFile,unGzFileDir.getPath());
            getAndHandleAllFile(unGzFileDir,file1 -> dealFile(file1));
        }catch (Exception e){
            System.out.printf("下载解压文件报错!");
        }finally {
            File[] files=unGzFileDir.listFiles();
            for(File item:files){
                item.delete();
            }
            gzFile.delete();
            unGzFileDir.delete();
        }
            //return flag;
    }

    private void test2(){
        File dirWithFile=new File("D:\\1164767564253204480");
        File dir=new File("D:\\1164767564253204481");
        File[] files=dirWithFile.listFiles();
        for(File file:files){
            file.delete();
        }
        System.out.println(dirWithFile.delete());
        System.out.println(dir.delete());
        System.out.println("");
    }

    private void test3(){
        File file=new File("D:\\download\\chromeDownload\\1300029038_20190821.csv");
        File[] files=file.listFiles();
        System.out.println(files[0]);
    }

    private void dealFile(File file){
        System.out.println("处理文件");
        System.out.println(file.getAbsoluteFile());
        System.out.println(file.getAbsolutePath());
        System.out.println(file.getName());
        System.out.println(file.getTotalSpace());
        System.out.println("处理文件结束");

    }
}
