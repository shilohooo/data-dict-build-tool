package org.shiloh.test.oracle;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.shiloh.entity.OracleTable;
import org.shiloh.mapper.oracle.OracleTableMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @author lxlei
 * @date 2020/10/12 16:07
 * @description
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OracleTableMapperTests {

    @Autowired
    private OracleTableMapper oracleTableMapper;

    @Value("${app.database.oracle.owner}")
    private String owner;

    private static final String TABLE_TYPE = "TABLE";

    @Test
    public void getAllTablesMetadataTest() {
        List<OracleTable> oracleTables = oracleTableMapper.findAllByOwner(TABLE_TYPE, owner);
        oracleTables.forEach(System.out::println);
    }
}
