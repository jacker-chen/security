package com.bjfn.securityjwt.config;

import com.bjfn.securityjwt.componet.*;
import com.bjfn.securityjwt.filter.JwtAuthorizationTokenFilter;
import com.bjfn.securityjwt.filter.MyFilterInvocationSecurityMetadataSource;
import com.bjfn.securityjwt.security.MyAccessDecisionManager;
import com.bjfn.securityjwt.security.UserDetailServiceImpl;
import com.bjfn.securityjwt.security.UserNameAuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    //未登录时处理器
    @Autowired
    private RestAuthorizationEntryPoint restAuthorizationEntryPoint;
    //登录成功处理器
    @Autowired
    private MyAuthenticationSuccessHandler myAuthenticationSuccessHandler;
    //登录失败处理器
    @Autowired
    private MyAuthenticationFailureHandler myAuthenticationFailureHandler;
    //登出成功处理器
    @Autowired
    private MyLogoutSuccessHandler myLogoutSuccessHandler;
    //无权限访问处理器
    @Autowired
    private RestfulAccessDeniedHandler restfulAccessDeniedHandler;
    //自定义用户验证
    @Autowired
    private UserDetailServiceImpl userDetailService;
    @Autowired
    private JwtAuthorizationTokenFilter jwtAuthorizationTokenFilter;
    //自定义用户密码验证
    @Autowired
    private UserNameAuthenticationProvider userNameAuthenticationProvider;
    //自定义资源权限过滤
    @Autowired
    private MyFilterInvocationSecurityMetadataSource myFilterInvocationSecurityMetadataSource;
    //自定义权限决策管理器
    @Autowired
    private MyAccessDecisionManager myAccessDecisionManager;


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //auth.userDetailsService(userDetailService).passwordEncoder(passwordEncoder());
        auth.authenticationProvider(userNameAuthenticationProvider);

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable().cors().and()
                //使用jwt不需要session
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)

                .and()
                .authorizeRequests()//定义哪些URL需要被保护、哪些不需要被保护
                .withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
                    @Override
                    public <O extends FilterSecurityInterceptor> O postProcess(O o) {
                        o.setAccessDecisionManager(myAccessDecisionManager);
                        o.setSecurityMetadataSource(myFilterInvocationSecurityMetadataSource);
                        return o;
                    }
                })
                .antMatchers("/login*","/swagger-ui.html")
                .anonymous()
                .anyRequest()//以下配置的请求,登录后可以访问
                .access("@rbacauthorityservice.hasPermission(request,authentication)") // RBAC 动态 url 认证
                .and()
                /*
                .formLogin()//开启登录
                //loginProcessingUrl指定前后端分离时登录接口
                .loginProcessingUrl("/login")
                .successHandler(myAuthenticationSuccessHandler)
                .failureHandler(myAuthenticationFailureHandler).permitAll()
                .and()*/
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessHandler(myLogoutSuccessHandler).permitAll();
        http.exceptionHandling()
                //未登录处理
                .authenticationEntryPoint(restAuthorizationEntryPoint)
                //已在全局异常里配置了无权限异常处理
                .accessDeniedHandler(restfulAccessDeniedHandler) // 无权访问 JSON 格式的数据
        ;
        //添加token认证过滤器
        http.addFilterBefore(jwtAuthorizationTokenFilter, UsernamePasswordAuthenticationFilter.class); // JWT Filter
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        //所需要用到的静态资源，允许访问
        web.ignoring().antMatchers( "/swagger-ui.html",
                "/swagger-ui/*",
                "/swagger-resources/**",
                "/v2/api-docs",
                "/v3/api-docs",
                "/webjars/**");
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }


}
