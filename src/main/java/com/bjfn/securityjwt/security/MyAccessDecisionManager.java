package com.bjfn.securityjwt.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Collection;

/**
 * 自定义资源决策管理器，对资源是否进行放行
 */

@Component
@Slf4j
public class MyAccessDecisionManager implements AccessDecisionManager {

    /**
     * decide 方法是判定是否拥有权限的决策方法，
     * @param authentication 当前用户的信息
     * @param o 包含客户端发起的请求的requset信息
     * @param collection  当前路径对应的权限
     * @throws AccessDeniedException
     * @throws InsufficientAuthenticationException
     */
    @Override
    public void decide(Authentication authentication, Object o, Collection<ConfigAttribute> collection) throws AccessDeniedException, InsufficientAuthenticationException {
        Object principal = authentication.getPrincipal();
        log.info("权限"+authentication.toString());
        //当接口未被配置时，直接放行
        if(CollectionUtils.isEmpty(collection)){
            return;
        }
        for (ConfigAttribute configAttribute : collection) {
            String attribute = configAttribute.getAttribute();
            Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
            log.info(authorities.toString());
            for (GrantedAuthority authority : authorities) {
                String userRole = authority.getAuthority();
                log.info("userROle:" + userRole);
                //数据库中没有保存ROLE_,这里添加上
                String menuRole = "ROLE_"+configAttribute.getAttribute();
                log.info("菜单角色："+menuRole);
                if (userRole.equals(menuRole)) {
                    //System.out.println("进入应用系统");
                    return;
                }
            }
        }
        throw new AccessDeniedException("权限不足，无法访问！");
    }

    @Override
    public boolean supports(ConfigAttribute configAttribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
