package test1.emojiTest;

import com.vdurmont.emoji.EmojiParser;

/**
 * Created by liaura_ljl on 2019/9/19.
 */
public class EmojiTest {
    public static void main(String[] args){
        String str = "An :grinning:awesome :smiley:string 😄with a few :wink:emojis :ac: :tv: :writing: :writing_hand: !";
        String result = EmojiParser.parseToUnicode(str);
        System.out.println(result);

        result = EmojiParser.parseToAliases(str);
        System.out.println(result);

    }
}
