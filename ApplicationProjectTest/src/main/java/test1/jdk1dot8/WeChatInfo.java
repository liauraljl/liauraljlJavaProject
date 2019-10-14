package test1.jdk1dot8;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class WeChatInfo {
    private String weChatId;
    private String weChatAccount;
    private String weChatNickName;
    private Integer sortId;
}
