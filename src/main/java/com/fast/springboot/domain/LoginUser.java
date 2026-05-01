package com.fast.springboot.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
/**
 * 登录用户身份类
 * 作用：封装登录用户的信息
 */
@Data
@NoArgsConstructor
public class LoginUser implements UserDetails {
    // 用户 ID
    private Long userId;
    // 登录时间戳，毫秒级
    private Long loginTime;
    // 过期时间戳，毫秒级
    private Long expireTime;
    // 用户的信息
    private User user;
    // 带参数的构造方法
    public LoginUser(Long userId, User user) {
        this.userId = userId;
        this.user = user;
    }
    // 获取用户权限集合
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }
    // 获取用户密码
    @Override
    public String getPassword() {
        return user.getPassword();
    }
    // 获取用户名
    @Override
    public String getUsername() {
        return user.getUserName();
    }
}
