package com.fast.springboot.service;

import com.fast.springboot.domain.User;

/**
 * 用户 service接口
 */
public interface UserService {
    /**
     * 通过用户名查询用户
     * @param userName 用户名
     * @return 用户对象信息
     */
    User selectUserByUserName(String userName);
}