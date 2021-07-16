package org.shiloh.runner.oracle;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import org.shiloh.entity.OracleColumn;
import org.shiloh.entity.OracleTable;
import org.shiloh.mapper.oracle.OracleColumnMapper;
import org.shiloh.mapper.oracle.OracleTableMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author lxlei
 * @date 2020/10/12 17:05
 */
@Component
@Order(1)
@ConditionalOnProperty(value = "app.database.type", havingValue = "oracle")
public class OracleDataDictExportRunner implements CommandLineRunner {

    private static final Logger LOG = LoggerFactory.getLogger(OracleDataDictExportRunner.class);

    @Resource
    private OracleTableMapper oracleTableMapper;

    @Resource
    private OracleColumnMapper oracleColumnMapper;

    @Value("${app.database.oracle.owner}")
    private String owner;

    /**
     * 数据字典文件存放路径
     */
    @Value("${app.excel-file-path}")
    private String excelFilePath;

    private static final String TABLE_TYPE = "TABLE";

    @Override
    public void run(String... args) {
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
