package com.lee;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @Auther: liyiyu
 * @Date: 2020/5/17 13:14
 * @Description:
 */

@EnableTransactionManagement(order = 10)//开启事务注解
@SpringBootApplication
public class Applicaition extends SpringBootServletInitializer implements EmbeddedServletContainerCustomizer {


    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Applicaition.class);
    }


    public static void main(String[] args) {
        SpringApplication.run(Applicaition.class,args);
    }

    @Override
    public void customize(ConfigurableEmbeddedServletContainer configurableEmbeddedServletContainer) {

    }
}
