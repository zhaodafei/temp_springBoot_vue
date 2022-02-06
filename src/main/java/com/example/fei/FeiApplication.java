package com.example.fei;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

// @SpringBootApplication
@MapperScan("com.example.*.mapper")
@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class})
public class FeiApplication {

    public static void main(String[] args) {
        SpringApplication.run(FeiApplication.class, args);

        System.out.println("" +
                "\033[32m  ♥◠‿◠)ﾉﾞ \033[0m " +
                "\033[35m  springBoot 项目启动成功,作者:大飞 \033[0m" +
                "\033[32m  ლ(´ڡ`ლ)ﾞ \033[0m " +
                "");
    }

}
