package com.oc.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Date;

/**
 * 用户统一认证表
 */
@Entity
@Table(name = "auth_user")
public class User extends BaseEntity {

    private static final long serialVersionUID = -8511714083272416828L;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 是否启用
     */
    private boolean enabled;

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
     * 角色
     */
    private Role role;

    /**
     * 原类型id
     */
    private Long originId;

    /**
     * 最后一次修改密码的时间
     */
    private Date lastPasswordReset;


    @Column(nullable = false, unique = true)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Column(nullable = false)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(nullable = false)
    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @ManyToOne
    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Column(nullable = false, unique = true)
    public Long getOriginId() {
        return originId;
    }

    public void setOriginId(Long originId) {
        this.originId = originId;
    }

    public Date getLastPasswordReset() {
        return lastPasswordReset;
    }

    public void setLastPasswordReset(Date lastPasswordReset) {
        this.lastPasswordReset = lastPasswordReset;
    }
}
