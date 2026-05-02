package com.fast.springboot.config;

import com.fast.springboot.configure.JwtAuthenticationEntryPoint;
import com.fast.springboot.configure.JwtAuthenticationTokenFilter;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * spring security 安全配置类
 * 这个类就像是整个系统的"保安队长"，负责配置系统的安全规则
 */
@EnableMethodSecurity(securedEnabled = true) // 启用方法级别的安全控制(就像给每个房间再加了一把锁)
@Configuration
public class SecurityConfig {
    @Resource
    private JwtAuthenticationEntryPoint authenticationEntryPoint;
    @Resource
    private JwtAuthenticationTokenFilter authenticationTokenFilter;

    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                // 1. 开启跨域，浏览器会先发送 OPTIONS 请求进行预检，需要服务端允许跨域，如果不配置 CORS，前端的 JWT Token 无法跨域传递
                .cors(Customizer.withDefaults())
                // 2. 关闭CSRF防护，JWT 本身不受 CSRF 攻击影响，关闭 CSRF 可简化配置。
                .csrf(AbstractHttpConfigurer::disable)
                // 3. 允许同源页面嵌入 iframe
                .headers(headers -> headers.frameOptions(frame -> frame.sameOrigin()))
                // 4. 无状态：不使用Session（JWT必备）服务端不保存会话
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                // 5. 认证失败处理
                .exceptionHandling(exception -> exception.authenticationEntryPoint(authenticationEntryPoint))
                //6.路径权限配置 - 定义哪些路径需要认证，哪些路径不需要认证
                .authorizeHttpRequests(req -> req
                        //公开接口 - 所有人都可以访问
                        .requestMatchers("/login", "/register").permitAll()
                        //其他所有请求都需要认证
                        .anyRequest().authenticated()
                )
                // 7. 退出登录处理
                .logout(logout -> logout.logoutUrl("/logout")
                        .logoutSuccessHandler((req, res, auth) -> res.setStatus(HttpServletResponse.SC_OK)))
                //8.添加JWT的过滤器 - 在用户名密码认证过滤器之前执行
                .addFilterBefore(authenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}