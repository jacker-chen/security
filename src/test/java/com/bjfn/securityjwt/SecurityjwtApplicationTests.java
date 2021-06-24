package com.bjfn.securityjwt;

import com.bjfn.securityjwt.componet.JwtTokenUtil;
import com.bjfn.securityjwt.mapper.SysUserDao;
import com.bjfn.securityjwt.pojo.SysUser;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
@Slf4j
class SecurityjwtApplicationTests {

    @Autowired
    private SysUserDao sysUserDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Test
    void contextLoads() {
        SysUser sysUser = new SysUser();
        sysUser.setUsername("bjfn");
        sysUser.setPassword("bjfn");
        String s = jwtTokenUtil.generateToken(sysUser);
        String usernameFromToken = jwtTokenUtil.getUsernameFromToken(s);

    }

}
