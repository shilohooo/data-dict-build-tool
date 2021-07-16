package org.shiloh;

import org.mybatis.spring.annotation.MapperScan;
import org.shiloh.config.DictExportConfig;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * @author lxlei
 * @date 2020-09-21 17:20:00
 */
@SpringBootApplication
@EnableConfigurationProperties(DictExportConfig.class)
@MapperScan(basePackages = {"org.shiloh.mapper"})
public class App {

    public static void main(String[] args) {
        new SpringApplicationBuilder(App.class).web(WebApplicationType.NONE)
                .run(args);
    }

}
