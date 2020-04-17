package com.wenjun.poas.aspect;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.wenjun.poas.client.SpiderClient;
import com.wenjun.poas.config.spider.SpiderConfig;
import com.wenjun.poas.entity.HttpResult;
import com.wenjun.poas.nlp.AsyncNlpProcessing;
import com.wenjun.poas.service.INlpService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.CodeSignature;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author xuwenjun
 * @date 2020/4/15
 */
@Aspect
@Component
public class SpiderMonitorAspect {
    @Resource
    AsyncNlpProcessing asyncNlpProcessing;

    @Pointcut("@annotation(com.wenjun.poas.aspect.annotation.SpiderMonitor)")
    public void control() {
    }

    @AfterReturning("control()")
    public void f(JoinPoint joinPoint) {
        Map<String, Object> params = getNameAndValue(joinPoint);
        asyncNlpProcessing.monitorSpider(params);
    }

    Map<String, Object> getNameAndValue(JoinPoint joinPoint) {
        Map<String, Object> param = new HashMap<>();

        Object[] paramValues = joinPoint.getArgs();
        String[] paramNames = ((CodeSignature) joinPoint.getSignature()).getParameterNames();

        for (int i = 0; i < paramNames.length; i++) {
            param.put(paramNames[i], paramValues[i]);
        }

        return param;
    }
}
