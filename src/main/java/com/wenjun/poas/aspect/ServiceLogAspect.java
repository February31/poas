package com.wenjun.poas.aspect;

import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.CodeSignature;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author xuwenjun
 * @date 2020/5/5
 */
@Log4j2
@Aspect
@Component
public class ServiceLogAspect {

    @Pointcut("@annotation(com.wenjun.poas.aspect.annotation.ServiceLog)")
    public void control() {
    }

    @AfterReturning("control()")
    public void f(JoinPoint joinPoint) {
        Map<String, Object> params = getNameAndValue(joinPoint);
        log.info(params);
    }

    private Map<String, Object> getNameAndValue(JoinPoint joinPoint) {
        Map<String, Object> param = new HashMap<>();

        Object[] paramValues = joinPoint.getArgs();
        String[] paramNames = ((CodeSignature) joinPoint.getSignature()).getParameterNames();

        for (int i = 0; i < paramNames.length; i++) {
            param.put(paramNames[i], paramValues[i]);
        }

        return param;
    }
}
