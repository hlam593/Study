package com.hl.dubbodemoconsumer;

import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.hl.DemoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDubbo
public class DubboDemoConsumerApplication {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @DubboReference(version = "1.0.0")
    private DemoService demoService;

    public static void main(String[] args) {
        SpringApplication.run(DubboDemoConsumerApplication.class, args);
    }

    @Bean
    public ApplicationRunner runner() {
        return args -> {
            logger.info(demoService.sayHello("mercyblitz"));
        };
    }

}
