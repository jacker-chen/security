package com.bjfn.securityjwt;

import com.bjfn.securityjwt.componet.JwtTokenUtil;
import com.bjfn.securityjwt.mapper.SysMenuDao;
import com.bjfn.securityjwt.mapper.SysRoleDao;
import com.bjfn.securityjwt.mapper.SysUserDao;
import com.bjfn.securityjwt.pojo.SysMenu;
import com.bjfn.securityjwt.pojo.SysRole;
import com.bjfn.securityjwt.pojo.SysUser;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@SpringBootTest
@Slf4j
class SecurityjwtApplicationTests {

    @Autowired
    private SysUserDao sysUserDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private SysRoleDao sysRoleDao;

    @Autowired
    private SysMenuDao sysMenuDao;

    @Test
    void contextLoads() {
        List<SysMenu> sysMenus = sysMenuDao.selectListSysMenu();
        for (SysMenu sysMenu : sysMenus){
            log.info(sysMenu.toString());
        }

    }


}
