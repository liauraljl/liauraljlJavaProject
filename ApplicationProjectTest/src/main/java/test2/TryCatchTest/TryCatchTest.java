package test2.TryCatchTest;

public class TryCatchTest {

    public static void main(String[] args) {
        new TryCatchTest().start();
    }

    private void start(){
        int a=0;
        //add(a);
        int b=a;
    }

    private boolean add(int a) throws Exception{
        try{
            a+=1;
            int b=a/0;
        }catch (Exception e){
            e.printStackTrace();
            throw new Exception("ss");
            //return false;
        }
        return true;
    }


}
