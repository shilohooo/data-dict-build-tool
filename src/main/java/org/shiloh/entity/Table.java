package org.shiloh.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @author lxlei
 * @date 2020/9/21 17:51
 * @description 表信息实体
 */
@Data
public class Table {

    /**
     * 表所属数据库名称
     */
    @ExcelProperty("表所属数据库名称")
    private String tableScheme;

    /**
     * 表名称
     */
    @ExcelProperty("表名称")
    private String tableName;

    /**
     * 创建时间
     */
    @ExcelProperty("创建时间")
    private Date createTime;

    /**
     * 表创建时使用的engine
     */
    @ExcelProperty("表创建时使用的engine")
    private String engineName;

    /**
     * 表中的数据行数
     */
    @ExcelProperty("表中的数据行数")
    private Long tableRows;

    /**
     * 表中的数据大小，单位：byte
     */
    @ExcelProperty("表中的数据大小，单位：byte")
    private Long dataLength;

    /**
     * 表中的索引大小，单位：byte
     */
    @ExcelProperty("表中的索引大小，单位：byte")
    private Long indexLength;

    /**
     * 表注释
     */
    @ExcelProperty("表注释")
    private String tableComment;

}
