package com.fast.springboot.service.impl;

import com.fast.springboot.domain.User;
import com.fast.springboot.mapper.UserMapper;
import com.fast.springboot.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * 用户 service实现方法
 */
//@Service 注解的作用
//1. 告诉spring这是一个服务层组件(bean)
//2. spring会自动创建这个类的实例并且管理它
@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;

    /**
     * 通过用户名查询用户
     * @param userName 用户名
     * @return 用户对象信息
     */
    @Override
    public User selectUserByUserName(String userName) {
        return userMapper.selectUserByUserName(userName);
    }
}
