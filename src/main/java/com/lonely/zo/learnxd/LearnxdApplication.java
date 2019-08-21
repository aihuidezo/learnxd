package com.lonely.zo.learnxd;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.lonely.zo.learnxd.mapper")
public class LearnxdApplication {

    public static void main(String[] args) {
        SpringApplication.run(LearnxdApplication.class, args);
    }

}
