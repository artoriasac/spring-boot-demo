package com.annotation.around;

import com.common.model.MemberInfo;
import com.common.model.Result;
import com.common.utils.MemberUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

/**
 * @Author:artorias
 * @Description:
 * @Date:create in 9:43 2018/7/31 0031
 * Modeified By:
 */
@Component
@Aspect
public class LoginAround {
    @Around("@annotation(com.annotation.Login)")
    public Object doInvoke(ProceedingJoinPoint joinPoint)throws Throwable{
        Object[] args = joinPoint.getArgs();
        if (args!=null&&args.length>0){
            for (Object obj:args) {
                if (obj instanceof HttpServletRequest){
                    HttpServletRequest request = (HttpServletRequest) obj;
                    MemberInfo memberInfo = MemberUtils.getMemberInfo(request);
                    if(memberInfo==null){
                       return new Result<>();
                    }
                }
            }
        }
        return joinPoint.proceed();
    }
}
