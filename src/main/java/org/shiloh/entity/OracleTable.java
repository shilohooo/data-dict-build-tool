package org.shiloh.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Oracle表元数据对应实体
 *
 * @author lxlei
 * @date 2020/10/12 11:57
 */
@Data
public class OracleTable implements RowMapper<OracleTable> {

    /**
     * 表名称
     */
    @ExcelProperty("表名称")
    private String tableName;

    /**
     * 表所属用户
     */
    @ExcelProperty("表所属用户")
    private String owner;

    /**
     * 表注释
     */
    @ExcelProperty("表注释")
    private String tableComment;

    @Override
    public OracleTable mapRow(ResultSet rs, int rowNum) throws SQLException {
        OracleTable oracleTable = new OracleTable();
        oracleTable.setTableName(rs.getString("tableName"));
        oracleTable.setOwner(rs.getString("owner"));
        oracleTable.setTableComment(rs.getString("tableComment"));
        return oracleTable;
    }

}
