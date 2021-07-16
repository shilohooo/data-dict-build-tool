package org.shiloh.mapper.mysql;

import org.apache.ibatis.annotations.Param;
import org.shiloh.entity.Table;

import java.util.List;

/**
 * @author lxlei
 * @date 2020/10/12 16:35
 */
public interface TableMapper {

    /**
     * 根据数据库名称查询所有表元数据
     *
     * @param tableSchema 数据库名称
     * @return 给定数据库下的所有表的元数据
     * @author shiloh
     * @date 2021/7/16 10:28
     */
    List<Table> findAllTablesMetadataByTableSchema(@Param("tableSchema") String tableSchema);
}
