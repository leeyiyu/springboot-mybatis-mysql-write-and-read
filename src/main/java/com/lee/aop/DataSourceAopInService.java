package com.lee.aop;

import lombok.extern.slf4j.Slf4j;
import com.lee.config.DataSourceContextHolder;
import com.lee.config.DataSourceType;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.core.PriorityOrdered;
import org.springframework.stereotype.Component;


/**
 * @Auther: liyiyu
 * @Date: 2020/5/17 14:27
 * @Description:
 */
//@Aspect
//@EnableAspectJAutoProxy(exposeProxy=true,proxyTargetClass=true)
//@Component
@Slf4j
public class DataSourceAopInService implements PriorityOrdered{




    @Before("execution(* com.lee.service..*.*(..)) "
            + " && @annotation(com.lee.annotation.ReadDataSource) ")
    public void setReadDataSourceType() {
        //如果已经开启写事务了，那之后的所有读都从写库读
        if(!DataSourceType.write.getType().equals(DataSourceContextHolder.getReadOrWrite())){
            DataSourceContextHolder.setRead();
        }

    }

    @Before("execution(* com.lee.service..*.*(..)) "
            + " && @annotation(com.lee.annotation.WriteDataSource) ")
    public void setWriteDataSourceType() {
        DataSourceContextHolder.setWrite();
    }

    @Override
    public int getOrder() {
        /**
         * 值越小，越优先执行
         * 要优于事务的执行
         * 在启动类中加上了@EnableTransactionManagement(order = 10)
         */
        return 1;
    }

}