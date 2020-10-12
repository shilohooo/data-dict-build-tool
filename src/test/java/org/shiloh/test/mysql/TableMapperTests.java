package org.shiloh.test.mysql;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.shiloh.entity.Table;
import org.shiloh.mapper.mysql.TableMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @author lxlei
 * @date 2020/10/12 17:02
 * @description
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TableMapperTests {

    @Autowired
    private TableMapper tableMapper;

    @Value("${app.database.mysql.table-schema}")
    private String tableSchema;

    @Test
    public void getAllTablesMetadataByTableSchemaTest() {
        List<Table> tables = tableMapper.findAllTablesMetadataByTableSchema(tableSchema);
        tables.forEach(System.out::println);
    }
}
