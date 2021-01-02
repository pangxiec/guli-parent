package com.example.demo.edu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author xmy
 * @date 2020/12/29 10:38
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.example"})
public class EduApplicattion {
    public static void main(String[] args) {
        SpringApplication.run(EduApplicattion.class,args);
    }
}
