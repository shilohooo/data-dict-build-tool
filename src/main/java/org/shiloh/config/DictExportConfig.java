package org.shiloh.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 数据字典导出相关配置
 *
 * @author shiloh
 * @date 2021/7/16 10:17
 */
@ConfigurationProperties(prefix = "app")
@Data
public class DictExportConfig {
    /**
     * 数据库类型，此配置将决定使用哪种类型的数据库导出数据字典，当前支持：mysql、oracle
     */
    private String dbType;

    /**
     * 需要导出数据字典的数据库名称
     */
    private String tableSchema;

    /**
     * Oracle表空间所属用户名
     */
    private String owner;

    /**
     * 数据字典导出位置，默认在D盘
     */
    private String exportPath;
}
