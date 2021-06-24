package com.bjfn.securityjwt.controller;

import com.bjfn.securityjwt.componet.JwtTokenUtil;
import com.bjfn.securityjwt.pojo.SysUser;
import com.bjfn.securityjwt.security.UserDetailServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/hello")
@Slf4j
public class HelloController {

    @Autowired
    private AuthenticationManager  authenticationManager;

    @Autowired
    private UserDetailServiceImpl userDetailService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @RequestMapping("/sayHello/{name}")
    public String sayHello(@PathVariable String name){
        return "你好" + name;
    }

    @PostMapping("/login")
    public String login(HttpServletRequest request, HttpServletResponse response, @RequestParam("userName") String userName,@RequestParam("password") String password){
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userName, password);
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authenticate);
        SysUser sysUser = new SysUser();
        sysUser.setUsername(userName);
        sysUser.setPassword(password);
        String s = jwtTokenUtil.generateToken(sysUser);
        return s;
    }

    /**
     * 基于注解权限授权，
     * @return
     */
    @RequestMapping("/test")
    @PreAuthorize("hasAuthority('oo')")
    public String authTest(){
        return "需要权限的方法oo";
    }

    @RequestMapping("/test1")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String authTest1(){
        return "需要权限的方法admin";
    }
}
