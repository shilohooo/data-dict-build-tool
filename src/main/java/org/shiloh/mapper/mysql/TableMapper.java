package org.shiloh.mapper.mysql;

import org.apache.ibatis.annotations.Param;
import org.shiloh.entity.Table;

import java.util.List;

/**
 * @author lxlei
 * @date 2020/10/12 16:35
 * @description
 */
public interface TableMapper {

    List<Table> findAllTablesMetadataByTableSchema(@Param("tableSchema") String tableSchema);
}
