package test1.stringTest;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PatternTest {
    public static void main(String[] args) {
        int count = 0;
        String regEx = "[\\u4e00-\\u9fa5]";
        String str = "AQWASD我们都是好孩子AAAA11222 ";
        char[] ch=str.trim().toCharArray();
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        while (m.find()) {
            for (int i = 0; i <= m.groupCount(); i++) {
                count = count + 1;
            }
        }
        System.out.println("共有 " + count + "个 ");

        System.out.println(checkPhone("122121"));
        System.out.println(checkPhone("13412121212"));
        System.out.println(checkPhone("fdsf"));
        System.out.println(checkPhone("21"));
        System.out.println(checkPhone("fdsf"));
        System.out.println(checkPhone("156545433"));
        System.out.println(checkPhone("12454234567"));
        System.out.println(checkPhone("gdg"));

    }

    private static boolean checkPhone(String str){
        String regex="1[3|4|5|7|8][0-9]\\d{8}";
        if (str.length() != 11) {
            return false;
        } else {
            Pattern p = Pattern.compile(regex);
            Matcher m = p.matcher(str);
            return m.matches();
        }
    }
}
