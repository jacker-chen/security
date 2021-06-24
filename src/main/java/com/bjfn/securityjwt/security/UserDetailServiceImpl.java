package com.bjfn.securityjwt.security;

import com.bjfn.securityjwt.mapper.SysUserDao;
import com.bjfn.securityjwt.pojo.SysUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
@Slf4j
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private SysUserDao sysUserDao;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        SysUser sysUser = sysUserDao.selectByName(s);
        if(sysUser == null){
            //仍需要细化处理
            throw new UsernameNotFoundException("该用户不存在");
        }
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority("ROLE_ADMIN");
        List<SimpleGrantedAuthority> authorityList = Arrays.asList(authority);
        sysUser.setGrantedAuthorities(authorityList);
        log.info("用户认证通过"+sysUser.toString());
        return sysUser;
    }
}
