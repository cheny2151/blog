package com.oc.entity.pojo;

/**
 * 角色
 */
public class Role extends BaseEntity {

    private static final long serialVersionUID = -960190065903091695L;

    /**
     * 用户认证枚举
     */
    public enum Authentication {

        ADMIN,

        BLOGGER

    }

    /**
     * 角色枚举
     */
    public enum OriginType {

        /**
         * 管理员
         */
        admin,

        /**
         * 博主
         */
        blogger

    }

    /**
     * 角色名
     */
    private String name;

    /**
     * 角色认证权限
     */
    private String auth;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuth() {
        return auth;
    }

    public void setAuth(String auth) {
        this.auth = auth;
    }

}