package com.bjfn.securityjwt.componet;

import com.bjfn.securityjwt.pojo.RespBean;
import com.bjfn.securityjwt.pojo.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * 登录成功后处理器
 */

@Component
public class MyAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        SysUser principal = (SysUser) authentication.getPrincipal();
        String token = jwtTokenUtil.generateToken(principal);
        RespBean success = RespBean.success("token:", token);
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json");
        response.getWriter().println(success);
        response.getWriter().flush();
        response.getWriter().close();
    }
}
