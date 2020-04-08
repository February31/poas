package com.wenjun.poas.config.security;

import com.alibaba.fastjson.JSON;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * 认证失败处理器
 * @author xuwenjun
 * @date 2020/3/30
 */
@Configuration
public class MyAuthenticationFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse response, AuthenticationException ex) throws IOException, ServletException {
        response.setContentType("application/json;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        PrintWriter out = response.getWriter();
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("code",401);
        if (ex instanceof UsernameNotFoundException || ex instanceof BadCredentialsException) {
            map.put("message","用户名或密码错误");
        } else if (ex instanceof DisabledException) {
            map.put("message","账户被禁用");
        } else {
            map.put("message","登录失败!");
        }
        out.write(JSON.toJSONString(map));
        out.flush();
        out.close();
    }
}
