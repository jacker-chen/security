package com.bjfn.securityjwt.controller;


import com.bjfn.securityjwt.componet.JwtTokenUtil;
import com.bjfn.securityjwt.mapper.SysUserDao;
import com.bjfn.securityjwt.pojo.LoginUser;
import com.bjfn.securityjwt.pojo.RespBean;
import com.bjfn.securityjwt.pojo.SysUser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@RestController
@Slf4j
@Api(tags = "登录接口")
public class LoginController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;


    @PostMapping("/login")
    @ApiOperation("用户登录")
    public RespBean login(HttpServletRequest request, HttpServletResponse response, LoginUser loginUser){
        log.info("自定义登录接口：接收到的参数：" + loginUser.toString());
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(loginUser.getUsername(), loginUser.getPassword());
        log.info(token.toString());
        Authentication authenticate = authenticationManager.authenticate(token);
        String s = jwtTokenUtil.generateToken(authenticate);

        return RespBean.success("token:",s);
    }
}
