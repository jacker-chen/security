package com.bjfn.securityjwt.componet;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bjfn.securityjwt.pojo.RespBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;


/**
 * 全局异常返回处理器
 */
@RestControllerAdvice
@ResponseBody
@Slf4j
public class GlobalExceptionHandler {

    /**
     * 处理其他所有未知的异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(value = {Exception.class})
    public Object globalExceptionHandler(Exception e) {
        log.error(e.getMessage(), e.getMessage());
        return JSON.toJSON(RespBean.error("请求错误",e.getMessage()));
    }

    @ExceptionHandler(value = {AccessDeniedException.class})
    public Object accessDeniedExceptionHandler(AccessDeniedException e){
        log.info("权限不足够");
        return JSON.toJSON(RespBean.error("权限不足够",e.getMessage()));
    }
}
