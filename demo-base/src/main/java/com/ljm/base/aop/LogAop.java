package com.ljm.base.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Slf4j
public class LogAop {

    // 切点
    @Pointcut("execution(* com.ljm.controller.web1..*.*(..))")
    public void logPointcut() {
    }

    // 环绕通知
    @Around("logPointcut()")
    public Object around(ProceedingJoinPoint pjp) {
        try {
            log.info("方法环绕通知开始");

            Object proceed = pjp.proceed();

            log.info("方法环绕通知结果：{}", proceed);

            return proceed;
        } catch (Throwable throwable) {
            log.error("方法环绕通知异常", throwable);
            throwable.printStackTrace();
        }
        return null;
    }

    // 前至通知
    @Before("logPointcut()")
    public void before(JoinPoint jp) {
        log.info("方法前置通知");
    }

    // 后置通知
    @After("logPointcut()")
    public void after(JoinPoint jp) {
        log.info("方法后置通知");
    }

    // 返回通知
    @AfterReturning(value = "logPointcut()", returning = "ret")
    public void afterReturn(Object ret) {
        log.info("返回通知，返回值：{}", ret);
    }

    // 异常通知
    @AfterThrowing("logPointcut()")
    public void afterThrowing(JoinPoint jp) {
        log.info("方法异常通知");
    }

}
