package org.shiloh.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * dataSource configuration
 *
 * @author lxlei
 * @date 2020/9/22 10:46
 */
@Configuration
public class DataSourceConfig {

    /**
     * 配置连接MySQL数据库的数据源
     *
     * @return javax.sql.DataSource
     * @author lxlei
     * @date 2020/10/9 16:42
     */
    @Bean
    @Qualifier("mysqlDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.mysql")
    @ConditionalOnProperty(value = "app.db-type", havingValue = "mysql")
    public DataSource mysqlDataSource() {
        return DataSourceBuilder.create().build();
    }

    /**
     * 配置连接Oracle数据库的数据源
     *
     * @return javax.sql.DataSource
     * @author lxlei
     * @date 2020/10/10 14:09
     */
    @Bean
    @Qualifier("oracleDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.oracle")
    @ConditionalOnProperty(value = "app.db-type", havingValue = "oracle")
    public DataSource oracleDataSource() {
        return DataSourceBuilder.create().build();
    }
}
