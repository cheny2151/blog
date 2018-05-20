package com.oc.entity.pojo;

/**
 * 博主
 */
public class Blogger extends UserBase {

    private static final long serialVersionUID = 5004361985367958141L;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 座右铭
     */
    private String motto;

    /**
     * 等级
     */
    private Integer level;

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getMotto() {
        return motto;
    }

    public void setMotto(String motto) {
        this.motto = motto;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

}
