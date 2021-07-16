package org.shiloh.mapper.mysql;

import org.apache.ibatis.annotations.Param;
import org.shiloh.entity.Column;

import java.util.List;

/**
 * @author lxlei
 * @date 2020/10/12 16:35
 */
public interface ColumnMapper {

    /**
     * 根据数据库名称和表名称查询指定表的所有列的元数据
     * @author lxlei
     * @date 2020/10/12 16:50
     * @param tableSchema 数据库名称
     * @param tableName 表名称
     * @return java.util.List<org.shiloh.entity.Column>
     */
    List<Column> findAllColumnsMetadataByTableSchemaAndTableName(@Param("tableSchema") String tableSchema,
                                                                 @Param("tableName") String tableName);
}
