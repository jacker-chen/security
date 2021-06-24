package com.bjfn.securityjwt.security;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Objects;


/**
 * 用户密码认证处理器
 */
@Slf4j
@Component
public class UserNameAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private UserDetailServiceImpl userDetailService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        long time = System.currentTimeMillis();
        log.info("用户名/密码 开始登录验证 time:{}", time);
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();
        // 1、去调用自己实现的UserDetailsService，返回UserDetails
        UserDetails userDetails = userDetailService.loadUserByUsername(username);
        // 2、密码进行检查，这里调用了PasswordEncoder，检查 UserDetails 是否可用。
        if (Objects.isNull(userDetails) || !passwordEncoder.matches(password, userDetails.getPassword())) {
        throw new BadCredentialsException("账号或密码错误");
    }
    // 3、返回经过认证的Authentication
    UsernamePasswordAuthenticationToken result = new UsernamePasswordAuthenticationToken(userDetails, null, Collections.emptyList());
        result.setDetails(authentication.getDetails());
        log.info("用户名/密码 登录验证完成 time:{}, existTime:{}", time, (System.currentTimeMillis() - time));
        return result;
}

    @Override
    public boolean supports(Class<?> authentication) {
        boolean res = UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
        log.info("用户名/密码 是否进行登录验证 res:{}", res);
        return res;
    }
}
