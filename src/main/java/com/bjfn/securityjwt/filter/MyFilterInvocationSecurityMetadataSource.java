package com.bjfn.securityjwt.filter;

import com.bjfn.securityjwt.mapper.SysMenuDao;
import com.bjfn.securityjwt.pojo.SysMenu;
import com.bjfn.securityjwt.pojo.SysRole;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


/**
 * 自定义权限资源，用于找出当前请求的url所需要的权限
 */
@Component
@Slf4j
public class MyFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {
    //用于比较匹配url
    AntPathMatcher pathMatcher = new AntPathMatcher();
    //资源查询
    @Autowired
    private SysMenuDao sysMenuDao;

    //查询出匹配当前url的所有role

    /**
     * 1.获取当前系统的所有资源
     * 2.通过当前请求的url匹配系统的所有资源，并得出需要请求当前url所需要的角色权限信息
     *
     * @param o
     * @return
     * @throws IllegalArgumentException
     */
    @Override
    public Collection<ConfigAttribute> getAttributes(Object o) throws IllegalArgumentException {
        //获取当前请求url
        String url = ((FilterInvocation) o).getRequestUrl();
        //获取数据库中所有的资源数据
        List<SysMenu> sysMenus = sysMenuDao.selectListSysMenu();
        log.info("请求的url"+url);
        log.info(sysMenus.size()+"");

        for (SysMenu menu : sysMenus) {

            log.info(menu.toString());
            log.info("菜单名称：" + menu.getMenuName());
            log.info(pathMatcher.match(menu.getMenuName(), url)+"");
            if (pathMatcher.match(menu.getMenuName(),url)) {
                List<SysRole> roles = menu.getRoleList();
                String[] roleStr = new String[roles.size()];
                for (int i = 0; i < roles.size(); i++) {
                    roleStr[i] = roles.get(i).getRoleName();
                }
                return SecurityConfig.createList(roleStr);
            }
        }
        return SecurityConfig.createList();
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
