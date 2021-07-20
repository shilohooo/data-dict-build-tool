package org.shiloh.runner.oracle;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.shiloh.config.DictExportConfig;
import org.shiloh.entity.OracleColumn;
import org.shiloh.entity.OracleTable;
import org.shiloh.mapper.oracle.OracleColumnMapper;
import org.shiloh.mapper.oracle.OracleTableMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @author lxlei
 * @date 2020/10/12 17:05
 */
@Component
@Order(1)
@ConditionalOnProperty(value = "app.db-type", havingValue = "oracle")
@RequiredArgsConstructor
@Slf4j
public class OracleDataDictExportRunner implements CommandLineRunner {
    private final OracleTableMapper oracleTableMapper;

    private final OracleColumnMapper oracleColumnMapper;

    private final DictExportConfig config;

    private static final String TABLE_TYPE = "TABLE";

    @Override
    public void run(String... args) {
        final ExcelWriter excelWriter = EasyExcel.write(config.getExportPath(), OracleColumn.class)
                .build();
        try {
            final List<OracleTable> oracleTables = oracleTableMapper.findAllByOwner(TABLE_TYPE, config.getOwner());
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
        log.info("Oracle数据字典导出完毕，存放位置：[{}]", config.getExportPath());
    }
}
