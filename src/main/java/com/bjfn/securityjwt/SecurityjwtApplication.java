package com.bjfn.securityjwt;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.bjfn.securityjwt.mapper")
public class SecurityjwtApplication {

    public static void main(String[] args) {
        SpringApplication.run(SecurityjwtApplication.class, args);
    }

}
