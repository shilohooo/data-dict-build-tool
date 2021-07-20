package org.shiloh.runner.mysql;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.shiloh.config.DictExportConfig;
import org.shiloh.entity.Column;
import org.shiloh.entity.Table;
import org.shiloh.mapper.mysql.ColumnMapper;
import org.shiloh.mapper.mysql.TableMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @author lxlei
 * @date 2020/10/12 11:33
 */
@Component
@Order(1)
@ConditionalOnProperty(value = "app.db-type", havingValue = "mysql")
@RequiredArgsConstructor
@Slf4j
public class MySqlDataDictExportRunner implements CommandLineRunner {
    private final TableMapper tableMapper;

    private final ColumnMapper columnMapper;

    private final DictExportConfig config;

    @Override
    public void run(String... args) {
        final ExcelWriter excelWriter = EasyExcel.write(config.getExportPath(), Column.class)
                .build();
        try {
            final List<Table> tables = tableMapper.findAllTablesMetadataByTableSchema(config.getTableSchema());
            List<Column> columns;
            WriteSheet writeSheet;
            for (Table table : tables) {
                columns = columnMapper.findAllColumnsMetadataByTableSchemaAndTableName(config.getTableSchema(),
                        table.getTableName());
                writeSheet = EasyExcel.writerSheet(StringUtils.isEmpty(table.getTableComment()) ? table.getTableName() :
                        String.format("%s(%s)", table.getTableName(), table.getTableComment()))
                        .build();
                excelWriter.write(columns, writeSheet);
            }
        } finally {
            if (excelWriter != null) {
                excelWriter.finish();
            }
        }
        log.info("MySQL数据字典导出完毕，存放位置为：[{}]", config.getExportPath());
    }
}
