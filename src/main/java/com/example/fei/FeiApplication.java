package com.example.fei;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

// @SpringBootApplication
// @CrossOrigin(origins = "http://demo.hexo.com")//表示只允许这一个url可以跨域访问这个controller
@MapperScan("com.example.*.mapper")
@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class})
public class FeiApplication {

    public static void main(String[] args) throws NoSuchAlgorithmException {
        SpringApplication.run(FeiApplication.class, args);

        System.out.println("" +
                "\033[32m  *** \033[0m " +
                "\033[35m springBoot 项目启动成功,作者:大飞 \033[0m" +
                "\033[32m  *** \n\r\033[0m " +
                // "\033[35m  美好的一天开始了, 请大家努力工作, 迎娶白富美, 嫁个高富帅, 踏上人生巅峰 \033[0m" +
                "\033[35m  美好的一天开始了, \n\r \033[0m" +
                "\033[35m  请大家努力工作, \n\r \033[0m" +
                "\033[35m  迎娶白富美,    \n\r \033[0m" +
                "\033[35m  嫁个高富帅, \n\r \033[0m" +
                "\033[35m  踏上人生巅峰 \n\r \033[0m" +
                "\033[32m ********************************* \033[0m " +
                "");
    }

}
