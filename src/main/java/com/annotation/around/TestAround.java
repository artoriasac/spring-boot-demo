package com.annotation.around;

import com.annotation.TestAnnotation;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * @Author:artorias
 * @Description:
 * @Date:create in 16:59 2018/7/30 0030
 * Modeified By:
 */
@Aspect
@Component
public class TestAround {

    @Around("@annotation(com.annotation.TestAnnotation)&&@annotation(test)")
    public Object doIvoke(ProceedingJoinPoint joinPoint,TestAnnotation test)throws Throwable{
        System.out.println("around success->"+test.value());
        return    joinPoint.proceed();
    }
}
