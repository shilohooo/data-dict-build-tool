package org.shiloh.test.mysql;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.shiloh.entity.Column;
import org.shiloh.mapper.mysql.ColumnMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @author lxlei
 * @date 2020/10/12 16:49
 * @description
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ColumnMapperTests {

    @Autowired
    private ColumnMapper columnMapper;

    /**
     * mysql数据库导出：需要导出数据字典的数据库名称
     */
    @Value("${app.database.mysql.table-schema}")
    private String mysqlTableSchema;

    @Test
    public void getAllColumnsMetadataByTableSchemaAndTableNameTest() {
        List<Column> columns = columnMapper.findAllColumnsMetadataByTableSchemaAndTableName(mysqlTableSchema,
                "tb_user");
        columns.forEach(System.out::println);

    }

}
