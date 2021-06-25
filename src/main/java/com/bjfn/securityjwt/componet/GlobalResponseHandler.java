package com.bjfn.securityjwt.componet;

import com.alibaba.fastjson.JSON;
import com.bjfn.securityjwt.pojo.RespBean;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;


/**
 * 全局controller层统一对象返回
 */
@ControllerAdvice(basePackages = "com.bjfn.securityjwt.controller")
public class GlobalResponseHandler implements ResponseBodyAdvice {
    @Override
    public boolean supports(MethodParameter methodParameter, Class aClass) {
        return true;//返回true 表示拦截 Controller 所有 API 接口的返回结果。
    }

    @Override
    public Object beforeBodyWrite(Object o, MethodParameter methodParameter, MediaType mediaType, Class aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        if(o instanceof RespBean){
            return JSON.toJSON(o);
        }
        if(o instanceof String){
            return JSON.toJSONString(RespBean.success("请求成功",o));
        }

        return JSON.toJSON(RespBean.success("请求成功",o));
    }
}
