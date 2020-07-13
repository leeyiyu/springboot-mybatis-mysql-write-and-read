package com.lee.aop;

import lombok.extern.slf4j.Slf4j;


import com.lee.config.DataSourceContextHolder;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @Auther: liyiyu
 * @Date: 2020/5/17 14:31
 * @Description:
 */
@Aspect
@EnableAspectJAutoProxy(exposeProxy=true,proxyTargetClass=true)
@Component
@Order(1)
public class DataSourceAopInDao {


    @Before("execution(* com.lee.dao..*.find*(..)) "
            + " || execution(* com.lee.dao..*.get*(..)) "
            + " || execution(* com.lee.dao..*.query*(..))")
    public void setReadDataSourceType() {
        DataSourceContextHolder.setRead();
    }

    @Before("execution(* com.lee.dao..*.insert*(..)) "
            + " || execution(* com.lee.dao..*.update*(..))"
            + " || execution(* com.lee.dao..*.add*(..))")
    public void setWriteDataSourceType() {
        DataSourceContextHolder.setWrite();
    }

}
