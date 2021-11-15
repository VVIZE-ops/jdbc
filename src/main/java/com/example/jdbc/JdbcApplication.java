package com.example.jdbc;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


//@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@SpringBootApplication(scanBasePackages = {"com.example"})
@MapperScan(basePackages = {"com.example.jdbc.dao"})
@EnableSwagger2

public class JdbcApplication {

    public static void main(String[] args) {
        SpringApplication.run(JdbcApplication.class, args);
    }

}
