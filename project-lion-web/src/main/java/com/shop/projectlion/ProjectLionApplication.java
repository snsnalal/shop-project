package com.shop.projectlion;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;


@SpringBootApplication
public class ProjectLionApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProjectLionApplication.class, args);
    }

}
