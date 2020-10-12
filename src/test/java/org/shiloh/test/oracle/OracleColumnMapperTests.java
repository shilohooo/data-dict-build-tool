package org.shiloh.test.oracle;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.shiloh.entity.OracleColumn;
import org.shiloh.mapper.oracle.OracleColumnMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @author lxlei
 * @date 2020/10/12 15:46
 * @description
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OracleColumnMapperTests {

    @Autowired
    private OracleColumnMapper oracleColumnMapper;

    private static final String TABLE_NAME = "ACCESS_LOG";

    @Test
    public void findAllColumnsMetadataByTableNameTest() {
        List<OracleColumn> oracleColumns = oracleColumnMapper.findAllColumnsMetadataByTableName(TABLE_NAME);
        oracleColumns.forEach(System.out::println);
    }
}
