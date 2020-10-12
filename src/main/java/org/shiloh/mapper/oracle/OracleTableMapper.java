package org.shiloh.mapper.oracle;

import org.apache.ibatis.annotations.Param;
import org.shiloh.entity.OracleTable;

import java.util.List;

/**
 * @author lxlei
 * @date 2020/10/12 15:50
 * @description
 */
public interface OracleTableMapper {

    /**
     * 根据用户查找表元数据
     * @author lxlei
     * @date 2020/10/12 14:28
     * @param tableType 表类型，默认为：TABLE
     * @param owner 用户
     * @return java.util.List<org.shiloh.entity.OracleTable>
     */
    List<OracleTable> findAllByOwner(@Param("tableType") String tableType, @Param("owner") String owner);
}
