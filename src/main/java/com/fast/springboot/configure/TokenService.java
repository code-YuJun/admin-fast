package com.fast.springboot.configure;

import com.fast.springboot.domain.LoginUser;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

/**
 * token 验证处理
 * 作用: JWT令牌的生成、解析、验证
 * 相当于系统的"身份证制作和检查站"
 */
@Component
public class TokenService {
    // 读取的是application.properties文件中的配置项
    //令牌自定义标识: HTTP请求头中的携带的token字段名
    @Value("${token.header}")
    private String header;
    //令牌密钥 用于加密/解密token的字符串
    @Value("${token.secret}")
    private String secret;

    //令牌有效期
    @Value("${token.expireTime}")
    private int expireTime;

    //JWT签名密钥: 将字符串secret转换成Key对象, 用于签名
    private Key secretKey;

    // JSON 处理器：对象和JSON字符串的相互转换
    @Resource
    private ObjectMapper objectMapper;

    /**
     * 系统启动时初始化密钥
     */
    @PostConstruct // 在 Bean 创建完之后，依赖注入完成后执行
    public void init() {
        // 将配置的令牌迷药字符串转换为 JWT 的可用的 Key 对象
        this.secretKey = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    }
    /**
     * 创建 token
     * 根据用户的信息创建一个 JWT 令牌，用户登录成功后调用
     */
    public String createToken(LoginUser loginUser) {
        //1.获取当前时间戳
        long now = System.currentTimeMillis();

        //2.计算过期时间(将分钟转换为毫秒)
        long expirationTime = now + TimeUnit.MINUTES.toMillis(expireTime);

        //3.更新用户信息中的时间字段
        loginUser.setLoginTime(now); //登录时间
        loginUser.setExpireTime(expirationTime); //过期时间

        //4.准备JWT的声明数据
        HashMap<String, Object> claims = new HashMap<>();

        //5.将LoginUser对象转换为Json字符串
        try {
            claims.put("user_key", objectMapper.writeValueAsString(loginUser));
        } catch (Exception e) {
            throw new RuntimeException("序列化用户信息失败", e);
        }

        //6.构建JWT token
        return Jwts.builder()
                .setClaims(claims) //设置声明数据
                .setExpiration(new Date(expirationTime)) //设置过期时间
                .signWith(secretKey) //用密钥签名
                .compact(); //生成字符串
    }

}