package com.fast.springboot.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 首页控制器，处理根路径请求
 */
@RestController //标记这个类是一个REST API控制器（它会自动把返回值转换为json格式）
@RequestMapping("/")
public class IndexController {
    @GetMapping
    public String home() {
        return "你好呀，恭喜你成功启动的后端~";
    }
}