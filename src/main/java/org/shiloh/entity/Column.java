package org.shiloh.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import lombok.Data;

import java.io.Serializable;

/**
 * 列信息实体
 *
 * @author lxlei
 * @date 2020/9/21 17:59
 */
@Data
@ColumnWidth(20)
public class Column implements Serializable {

    /**
     * 列属性所在的表所属的数据库名称
     */
    @ExcelProperty("所属数据库名称")
    private String tableScheme;

    /**
     * 列属性所在的表名称
     */
    @ExcelProperty("所属表名称")
    private String tableName;

    /**
     * 列名称
     */
    @ExcelProperty("列名称")
    private String columnName;

    /**
     * 列类型
     */
    @ExcelProperty("列类型")
    private String columnType;

    /**
     * 列的值是否可以为null，NO或YES
     */
    @ExcelProperty("值是否可为null")
    private String isNullable;

    /**
     * 列的默认值
     */
    @ExcelProperty("默认值")
    private String columnDefault;

    /**
     * 列的约束（主键，unique等）
     */
    @ExcelProperty("约束")
    private String columnKey;

    /**
     * 列的额外信息，例如：主键自增会有auto_increment
     */
    @ExcelProperty("额外信息")
    private String extra;

    /**
     * 列注释
     */
    @ExcelProperty("注释")
    private String columnComment;
}
