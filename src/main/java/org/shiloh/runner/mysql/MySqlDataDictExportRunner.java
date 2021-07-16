package org.shiloh.runner.mysql;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import lombok.RequiredArgsConstructor;
import org.shiloh.App;
import org.shiloh.config.DictExportConfig;
import org.shiloh.entity.Column;
import org.shiloh.entity.Table;
import org.shiloh.mapper.mysql.ColumnMapper;
import org.shiloh.mapper.mysql.TableMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
public class MySqlDataDictExportRunner implements CommandLineRunner {

    private final TableMapper tableMapper;

    private final ColumnMapper columnMapper;

    private final DictExportConfig config;

    private static final Logger LOG = LoggerFactory.getLogger(App.class);

    @Override
    public void run(String... args) {
        ExcelWriter excelWriter = EasyExcel.write(config.getExportPath(), Column.class)
                .build();
        try {
            List<Table> tables = tableMapper.findAllTablesMetadataByTableSchema(config.getTableSchema());
            List<Column> columns;
            com.alibaba.excel.write.metadata.WriteSheet writeSheet;
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
        LOG.info("MySQL数据字典导出完毕，存放位置为：[{}]", config.getExportPath());
    }
}
