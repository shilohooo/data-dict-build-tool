package org.shiloh.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import lombok.Data;

/**
 * Oracle列元数据对应实体
 *
 * @author lxlei
 * @date 2020/10/12 11:57
 */
@Data
@ColumnWidth(20)
public class OracleColumn {

    /**
     * 列所属表名称
     */
    @ExcelProperty("列所属表名称")
    private String tableName;

    /**
     * 列名称
     */
    @ExcelProperty("列名称")
    private String columnName;

    /**
     * 数据类型
     */
    @ExcelProperty("数据类型")
    private String dataType;

    /**
     * 数据长度
     */
    @ExcelProperty("数据长度")
    private Integer dataLength;

    /**
     * 数值精度
     */
    @ExcelProperty("数值精度")
    private Integer dataScale;

    /**
     * 是否可为null
     */
    @ExcelProperty("是否可为null")
    private String isNullable;

    /**
     * 默认值
     */
    @ExcelProperty("默认值")
    private String dataDefault;

    /**
     * 是否为主键
     */
    @ExcelProperty("是否为主键")
    private String isPrimaryKey;

    /**
     * 是否为外键
     */
    @ExcelProperty("是否为外键")
    private String isForeignKey;

    /**
     * 值是否唯一
     */
    @ExcelProperty("值是否唯一")
    private String isUnique;

    /**
     * 列注释
     */
    @ExcelProperty("列注释")
    private String columnComment;
}
