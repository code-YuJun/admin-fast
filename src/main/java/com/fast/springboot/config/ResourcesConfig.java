package com.fast.springboot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Collections;

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
     * @param registry 资源处理器注册器, 文件管家
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 本地文件上传路径配置
        // 例如: /profile/123.jpg 就去 ./file/123.jpg 找到对应的文件
        registry.addResourceHandler("/profile/**")
                .addResourceLocations("file:" + fastConfig.getProfile() + "/");
        // "file: " 表示从本地文件系统读取文件
    }
    @Bean //告诉spring: 这个方法返回的对象要放到容器里进行管理
    public CorsConfigurationSource corsConfigurationSource() {
        //创建一个跨域配置对象，用于配置跨域访问规则
        CorsConfiguration config = new CorsConfiguration();

        //1. 允许哪些来源访问(允许所有来源)
        config.setAllowedOriginPatterns(Collections.singletonList("*"));

        //2. 允许携带哪些请求头
        config.setAllowedHeaders(Collections.singletonList("*"));

        //3. 允许哪些HTTP方法(GET POST PUT DELETE等等)
        config.setAllowedMethods(Collections.singletonList("*"));

        //4. 是否允许发送凭证(如: cookies HTTP认证信息)
        //设置为true, 表示允许前端在请求时携带cookies等信息
        config.setAllowCredentials(true);

        //5. 预检请求的缓存时间(单位: 秒)
        //浏览器在发送复杂请求时(比如PUT DELETE等)之前, 会先发送一个OPTIONS预检请求
        //这个设置就是告诉浏览器: 1800秒(30分钟)之内, 不要再给我发送预检请求
        config.setMaxAge(1800L);
        //创建基于URL的跨域配置源
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        //注册配置: 对所有路径(/**)应用上面的跨域规则
        //就像是: 整个园区的所有大门都使用同一套通行规则
        source.registerCorsConfiguration("/**", config);
        return source;
    }
}