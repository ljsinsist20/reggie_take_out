package com.itheima.reggie;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

/**
 * @author: ljs
 * @Pcakage: com.itheima.reggie.ReggieApplication
 * @Date: 2022年06月26日 12:00
 * @Description:
 */
@Slf4j
@SpringBootApplication
@MapperScan("com.itheima.reggie.mapper")
@ServletComponentScan
public class ReggieApplication {
    public static void main(String[] args) {
        SpringApplication.run(ReggieApplication.class,args);
        log.info("项目启动成功...");
    }
}