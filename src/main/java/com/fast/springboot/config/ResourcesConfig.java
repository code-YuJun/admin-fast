package com.fast.springboot.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 资源处理配置类
 *
 * 这个类主要负责两件事情
 * 1. 配置静态资源访问路径(比如用户上传的图片、文件等等)
 * 2. 配置跨域访问规则(允许前端应用访问后端API)
 *
 * 可以把这个类看做是系统的"接待处"和"通行证办理处"
 */
@Configuration //告诉spring: 这是一个配置类, 启动时要加载
public class ResourcesConfig implements WebMvcConfigurer {
    /**
     * 配置静态资源处理器
     *
     * 这个方法负责告诉spring: 当用户请求某些路径时,
     * 应该去服务器的哪个文件夹里找到对应的文件
     *
     * @param registry
     */
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 本地文件上传路径配置
        // 例如: /profile/123.jpg 就去 ./file/123.jpg 找到对应的文件
        registry.addResourceHandler("/profile/**")
                .addResourceLocations("file:" + fastConfig.getProfile() + "/");
        // "file: " 表示从本地文件系统读取文件
    }
}