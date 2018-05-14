package com.oc.utils.jwt;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

/**
 * JsonWebToken 安全认证用户信息
 */
public class JwtPrincipal implements UserDetails {

    private static final long serialVersionUID = 2764317447462499613L;

    private Collection<? extends GrantedAuthority> authorities;

    private String username;

    private String password;

    private boolean accountNonExpired;

    private boolean accountNonLocked;

    private boolean enabled;

    private Date lastPasswordReset;

    public JwtPrincipal(Collection<? extends GrantedAuthority> authorities, String username, String password, Date lastPasswordReset, boolean enabled) {
        this.authorities = authorities;
        this.username = username;
        this.password = password;
        this.lastPasswordReset = lastPasswordReset;
        this.enabled = enabled;
        this.accountNonExpired = true;
        this.accountNonLocked = true;
    }

    public JwtPrincipal(Collection<? extends GrantedAuthority> authorities, String username, String password, Date lastPasswordReset, boolean accountNonExpired, boolean accountNonLocked, boolean enabled) {
        this.authorities = authorities;
        this.username = username;
        this.password = password;
        this.lastPasswordReset = lastPasswordReset;
        this.accountNonExpired = accountNonExpired;
        this.accountNonLocked = accountNonLocked;
        this.enabled = enabled;
    }

    /**
     * 角色列表
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    /**
     * 密码
     */
    @Override
    public String getPassword() {
        return password;
    }

    /**
     * 用户名
     */
    @Override
    public String getUsername() {
        return username;
    }

    /**
     * 账号是否未过期
     */
    @Override
    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    /**
     * 账号是否未锁定
     */
    @Override
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    /**
     * 密码是否未过期
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * 账号是否启用
     */
    @Override
    public boolean isEnabled() {
        return enabled;
    }

    /**
     * 最后一次修改密码
     */
    public Date getLastPasswordReset() {
        return lastPasswordReset;
    }

    /**
     * 获取认证权限数组
     */
    public String[] getAuthoritiesValues() {
        String[] values = new String[authorities.size()];
        int i = 0;
        for (GrantedAuthority authority : this.authorities) {
            values[i++] = authority.toString();
        }
        return values;
    }
}
