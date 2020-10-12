package org.shiloh.test.oracle;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.shiloh.entity.OracleColumn;
import org.shiloh.entity.OracleTable;
import org.shiloh.mapper.oracle.OracleColumnMapper;
import org.shiloh.mapper.oracle.OracleTableMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @author lxlei
 * @date 2020/10/12 14:49
 * @description
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ExportOracleTableMetadata2ExcelTests {

    private static final Logger LOG = LoggerFactory.getLogger(ExportOracleTableMetadata2ExcelTests.class);

    @Autowired
    private OracleTableMapper oracleTableMapper;

    @Autowired
    private OracleColumnMapper oracleColumnMapper;

    @Value("${app.database.oracle.owner}")
    private String owner;

    /**
     * 数据字典文件存放路径
     */
    @Value("${app.excel-file-path}")
    private String excelFilePath;

    private static final String TABLE_TYPE = "TABLE";

    @Test
    public void exportTest01() {
        ExcelWriter excelWriter = EasyExcel.write(excelFilePath, OracleColumn.class)
                .build();
        try {
            List<OracleTable> oracleTables = oracleTableMapper.findAllByOwner(TABLE_TYPE, owner);
            List<OracleColumn> oracleColumns;
            WriteSheet writeSheet;
            String sheetName;
            for (OracleTable oracleTable : oracleTables) {
                oracleColumns = oracleColumnMapper.findAllColumnsMetadataByTableName(oracleTable.getTableName());
                sheetName = StringUtils.isEmpty(oracleTable.getTableComment()) ? oracleTable.getTableName() :
                        String.format("%s(%s)", oracleTable.getTableName(), oracleTable.getTableComment());
                writeSheet = EasyExcel.writerSheet(sheetName)
                        .build();
                excelWriter.write(oracleColumns, writeSheet);
            }
        } finally {
            if (excelWriter != null) {
                excelWriter.finish();
            }
        }
        LOG.info("Oracle数据字典导出完毕，存放位置：[{}]", excelFilePath);
    }
}
