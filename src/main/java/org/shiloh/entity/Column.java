package org.shiloh.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import lombok.Data;
import org.springframework.jdbc.core.RowMapper;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 列信息实体
 *
 * @author lxlei
 * @date 2020/9/21 17:59
 */
@Data
@ColumnWidth(20)
public class Column implements Serializable, RowMapper<Column> {

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

    @Override
    public Column mapRow(ResultSet resultSet, int i) throws SQLException {
        Column column = new Column();
        column.setTableScheme(resultSet.getString("TABLE_SCHEMA"));
        column.setTableName(resultSet.getString("TABLE_NAME"));
        column.setColumnName(resultSet.getString("COLUMN_NAME"));
        column.setColumnType(resultSet.getString("COLUMN_TYPE"));
        column.setIsNullable(resultSet.getString("IS_NULLABLE"));
        column.setColumnDefault(resultSet.getString("COLUMN_DEFAULT"));
        column.setColumnKey(resultSet.getString("COLUMN_KEY"));
        column.setExtra(resultSet.getString("EXTRA"));
        column.setColumnComment(resultSet.getString("COLUMN_COMMENT"));
        return column;
    }

}
