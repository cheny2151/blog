package com.oc.entity;

import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class User {

    /**
     * 昵称
     */
    private String nickName;
    /**
     * 账号
     */
    private String account;
    /**
     * 密码
     */
    private String password;
    /**
     * 创建时间
     */
    private Date createDate;
    /**
     * 座右铭
     */
    private String motto;
    /**
     * 登陆状态
     */
    private Boolean loginStatus;
    /**
     * 等级
     */
    private Integer level;

    public User() {
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getMotto() {
        return motto;
    }

    public void setMotto(String motto) {
        this.motto = motto;
    }

    public Boolean getLoginStatus() {
        return loginStatus;
    }

    public void setLoginStatus(Boolean loginStatus) {
        this.loginStatus = loginStatus;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    @Override
    public String toString() {
        return "User{" +
                "nickName='" + nickName + '\'' +
                ", account='" + account + '\'' +
                ", password='" + password + '\'' +
                ", createDate=" + createDate +
                ", motto='" + motto + '\'' +
                ", loginStatus=" + loginStatus +
                ", level=" + level +
                '}';
    }
}
