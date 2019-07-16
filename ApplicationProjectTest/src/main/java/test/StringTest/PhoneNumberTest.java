package test.StringTest;

/**
 * Created by liaura_ljl on 2018/6/26.
 */
public class PhoneNumberTest {
    public static void main(String[] args){
        String phoneNumStr="15623139087";
        String telRegex = "[1][3578]\\d{9}";
        if(phoneNumStr.matches(telRegex)){
            System.out.print(true);
        }else{
            System.out.print(false);
        }

    }
}
