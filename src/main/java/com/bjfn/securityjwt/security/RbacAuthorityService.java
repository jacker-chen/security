package com.bjfn.securityjwt.security;

import com.bjfn.securityjwt.pojo.SysUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;


/**
 * 权限认证类
 */
@Component("rbacauthorityservice")
@Slf4j
public class RbacAuthorityService {
    public boolean hasPermission(HttpServletRequest request, Authentication authentication) {

        Object userInfo = authentication.getPrincipal();

        boolean hasPermission  = false;

        if (userInfo instanceof SysUser) {

            String username = ((SysUser) userInfo).getUsername();

            //获取资源
            List<String> urls = new ArrayList<>();
            // 这些 url 都是要登录后才能访问，且其他的 url 都不能访问！
            urls.add("/jwt/**");


            AntPathMatcher antPathMatcher = new AntPathMatcher();

            for (String url : urls) {
                if (antPathMatcher.match(url, request.getRequestURI())) {
                    hasPermission = true;
                    break;
                }
            }

            return hasPermission;
        } else {
            return false;
        }
    }
}
