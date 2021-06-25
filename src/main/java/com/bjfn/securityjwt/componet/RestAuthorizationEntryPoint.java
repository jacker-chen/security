package com.bjfn.securityjwt.componet;

import com.bjfn.securityjwt.pojo.RespBean;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


/**
 * 自定义未登录处理器
 */
@Component
@Slf4j
public class RestAuthorizationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
        log.info(e.toString());
        // 通过response设置编码格式

        response.setCharacterEncoding("UTF-8");
        // 设置ContentType
        response.setContentType("application/json");
        // 输出流
        PrintWriter out = response.getWriter();

        RespBean bean = RespBean.error("请登录"+e.getMessage());
        bean.setCode(401);
        out.write(new ObjectMapper().writeValueAsString(bean));
        out.flush();
        out.close();
    }
}
