package org.shiloh.runner.mysql;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import org.shiloh.App;
import org.shiloh.entity.Column;
import org.shiloh.entity.Table;
import org.shiloh.mapper.mysql.ColumnMapper;
import org.shiloh.mapper.mysql.TableMapper;
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
 * @date 2020/10/12 11:33
 * @description
 */
@Component
@Order(1)
@ConditionalOnProperty(value = "app.database.type", havingValue = "mysql")
public class MySqlDataDictExportRunner implements CommandLineRunner {

    @Resource
    private TableMapper tableMapper;

    @Resource
    private ColumnMapper columnMapper;

    /**
     * mysql数据库导出：需要导出数据字典的数据库名称
     */
    @Value("${app.database.mysql.table-schema}")
    private String mysqlTableSchema;

    /**
     * 数据字典文件存放路径
     */
    @Value("${app.excel-file-path}")
    private String excelFilePath;

    private static final Logger LOG = LoggerFactory.getLogger(App.class);

    @Override
    public void run(String... args) {
        ExcelWriter excelWriter = EasyExcel.write(excelFilePath, Column.class)
                .build();
        try {
            List<Table> tables = tableMapper.findAllTablesMetadataByTableSchema(mysqlTableSchema);
            List<Column> columns;
            com.alibaba.excel.write.metadata.WriteSheet writeSheet;
            for (Table table : tables) {
                columns = columnMapper.findAllColumnsMetadataByTableSchemaAndTableName(mysqlTableSchema,
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
        LOG.info("MySQL数据字典导出完毕，存放位置为：[{}]", excelFilePath);
    }
}
