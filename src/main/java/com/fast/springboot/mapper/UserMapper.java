package com.fast.springboot.mapper;

import com.fast.springboot.domain.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户 Mapper
 */
// @Mapper注解的作用:
// 1. 告诉mybatis这是一个Mapper接口
// 2. mybatis会自动为这个接口生成代理实现类
// 3. SpringBoot启动时会扫描@Mapper注解的接口, 并且注册为Bean
@Mapper
public interface UserMapper {
    /**
     * 根据用户名查询用户
     * @param userName 用户名
     * @return 用户对象信息
     */
    User selectUserByUserName(String userName);
    /**
     * 根据用户ID查询用户
     * @param userId 用户ID
     * @return 用户对象信息
     */
    User selectUserByUserId(Long userId);
}