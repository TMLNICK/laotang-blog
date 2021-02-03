package com.personblog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class PersonBlogApplication {

    public static void main(String[] args) {
        SpringApplication.run(PersonBlogApplication.class, args);
    }



}
