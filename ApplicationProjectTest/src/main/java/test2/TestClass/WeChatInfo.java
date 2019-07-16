package test2.TestClass;

import java.io.Serializable;

public class WeChatInfo implements Serializable {

    private static final long serialVersionUID=1L;

    private String weChatId;

    private String weChatAccount;

    private String weChatNickName;

    private Integer sortId;

    public String getWeChatId() {
        return weChatId;
    }

    public void setWeChatId(String weChatId) {
        this.weChatId = weChatId;
    }

    public String getWeChatAccount() {
        return weChatAccount;
    }

    public void setWeChatAccount(String weChatAccount) {
        this.weChatAccount = weChatAccount;
    }

    public String getWeChatNickName() {
        return weChatNickName;
    }

    public void setWeChatNickName(String weChatNickName) {
        this.weChatNickName = weChatNickName;
    }

    public Integer getSortId() {
        return sortId;
    }

    public void setSortId(Integer sortId) {
        this.sortId = sortId;
    }

    public WeChatInfo(String weChatId, String weChatAccount, String weChatNickName, Integer sortId) {
        this.weChatId = weChatId;
        this.weChatAccount = weChatAccount;
        this.weChatNickName = weChatNickName;
        this.sortId = sortId;
    }

    @Override
    public String toString() {
        return "WeChatInfo{" +
                "weChatId='" + weChatId + '\'' +
                ", weChatAccount='" + weChatAccount + '\'' +
                ", weChatNickName='" + weChatNickName + '\'' +
                ", sortId=" + sortId +
                '}';
    }
}
