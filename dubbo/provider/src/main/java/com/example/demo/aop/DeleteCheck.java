package com.example.demo.aop;

import com.example.common.annotation.Deleted;
import com.example.common.util.ThreadLocalUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * deleted 注解拦截
 *
 * @author wenfeng
 * @date 2020年 05月27日 11:20:59
 */
@Aspect
@Slf4j
@Component
public class DeleteCheck {
    @Pointcut("@annotation(com.example.common.annotation.Deleted)")
    public void check() {
    }

    @Before("check()")
    public void setCheck(JoinPoint joinPoint) {
        MethodSignature methodSignature = (MethodSignature)joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        Deleted deleted = method.getAnnotation(Deleted.class);
        String d = deleted.deleted();
        ThreadLocalUtil.setThreadLocal(d);
    }

    @After("check()")
    public void endCheck(JoinPoint joinPoint) {
        ThreadLocalUtil.cleanThreadLocal();
    }

}
