package com.fast.springboot.controller;

import com.fast.springboot.configure.TokenService;
import com.fast.springboot.domain.AjaxResult;
import com.fast.springboot.domain.LoginBody;
import com.fast.springboot.domain.LoginUser;
import com.fast.springboot.domain.User;
import com.fast.springboot.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * ClassName: LoginController
 * Package: com.fast.springboot.controller
 */
@RestController
public class LoginController extends BaseController {
    @Resource
    private UserService userService;
    @Resource
    private TokenService tokenService;
    /**
     * 登录接口
     */
    @PostMapping("/login")
    public AjaxResult login(@RequestBody LoginBody loginBody) {
        //步骤1: 验证参数是否为空
        if (loginBody.getUserName() == null || loginBody.getPassword() == null
                || loginBody.getUserName().trim().isEmpty() || loginBody.getPassword().trim().isEmpty()) {
            //抛出异常
            throw new RuntimeException("用户名或密码为空");
        }
        //步骤2: 验证用户是否存在
        User user = userService.selectUserByUserName(loginBody.getUserName());
        if (user == null) {
            throw new RuntimeException("用户名错误");
        }

        //步骤3: 验证密码是否正确
        if (!loginBody.getPassword().equals(user.getPassword())) {
            throw new RuntimeException("密码错误");
        }

        //步骤4: 登录成功，创建用户登录对象
        LoginUser loginUser = new LoginUser(user.getUserId(), user);

        // 步骤5: 生成 JWT 令牌
        String token = tokenService.createToken(loginUser);

        // 步骤6: 返回成功响应，包含 token
        return success().put("token", token);
    }

}