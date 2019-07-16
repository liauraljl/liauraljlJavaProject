package test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by liaura_ljl on 2018/1/4.
 */
public class MatcherTest1 {
    public static void main(String[] args){
        String str = "梵蒂冈dfsdf{abcd//ab}gfd是冈dfsdf{abcd\\ab}gf发冈dfsdf{dfdf/fd}gf生纠纷";
        Pattern pattern = Pattern.compile("(?<=\\{)(.+?)(?=\\})");
        Matcher matcher = pattern.matcher(str);
        while(matcher.find()){
            String matcherStr = matcher.group();
            String[] dataObj = matcherStr.split("/");
            System.out.println("Group:"+matcher.group());//

        }
    }
}
