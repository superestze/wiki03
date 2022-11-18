package com.zhengqi.wiki03.config;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
// import springfox.documentation.swagger2.annotations.EnableSwagger2;

@ComponentScan("com.zhengqi")
@SpringBootApplication
@MapperScan("com.zhengqi.wiki03.mapper")
@EnableScheduling
@EnableAsync
public class Wiki03Application {

    private final static Logger LOG = LoggerFactory.getLogger(Wiki03Application.class);

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(Wiki03Application.class);
        Environment env = app.run(args).getEnvironment();
        LOG.info("启动成功！");
        LOG.info("api 地址：\thttp:127.0.0.1:{}", env.getProperty("server.port"));
        LOG.info("swagger 地址：\thttp:127.0.0.1:{}/swagger.html", env.getProperty("server.port"));
        // LOG.info("swagger-layui 地址：\thttp:127.0.0.1:{}/docs.html", env.getProperty("server.port"));
        LOG.info("swagger-bootstrap 地址：\thttp:127.0.0.1:{}/doc.html", env.getProperty("server.port"));
    }
}
