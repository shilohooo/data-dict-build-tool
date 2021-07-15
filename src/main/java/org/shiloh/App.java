package org.shiloh;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * @author lxlei
 * @date 2020-09-21 17:20:00
 * @description 数据字典导出
 */
@SpringBootApplication
@MapperScan(basePackages = {"org.shiloh.mapper"})
public class App {

    public static void main(String[] args) {
        new SpringApplicationBuilder(App.class).web(WebApplicationType.NONE)
                .run(args);
    }

}
