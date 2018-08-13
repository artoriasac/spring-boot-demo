package com.annotation;

import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Service;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author:artorias
 * @Description:
 * @Date:create in 14:33 2018/8/10 0010
 * Modeified By:
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Service
public @interface MyService {

    @AliasFor(annotation=Service.class)
    String value()default "";
}
