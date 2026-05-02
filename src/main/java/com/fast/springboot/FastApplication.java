package com.fast.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

// 排除数据源的自动配置，因为需要自定义数据源的配置
@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
public class FastApplication {
    // 整个 java 程序的入口点
    public static void main(String[] args) {
        // 启动 spring boot 应用
        SpringApplication.run(FastApplication.class, args);
        // 打印日志
        System.out.println("==============后端启动成功==============");
    }
}
