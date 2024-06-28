package com.patsi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
//@EnableScheduling
@EnableFeignClients
@ComponentScan({"com.common.validation", "com.patsi.*"})
@EntityScan({"com.common.*", "com.patsi.*"})
@EnableJpaRepositories(basePackages = {"com.common.*", "com.patsi.*"})
public class MainApplication {
    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class, args);
    }

}
