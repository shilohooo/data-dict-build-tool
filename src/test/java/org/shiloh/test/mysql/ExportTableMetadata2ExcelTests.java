package org.shiloh.test.mysql;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.shiloh.entity.Column;
import org.shiloh.entity.Table;
import org.shiloh.mapper.mysql.ColumnMapper;
import org.shiloh.mapper.mysql.TableMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @author lxlei
 * @date 2020/9/22 14:13
 * @description
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ExportTableMetadata2ExcelTests {

    @Autowired
    private TableMapper tableMapper;

    @Autowired
    private ColumnMapper columnMapper;

    @Value("${app.database.mysql.table-schema}")
    private String tableSchema;

    @Value("${app.excel-file-path}")
    private String excelFilePath;

    @Test
    public void exportTableMetadata2Excel() {
        ExcelWriter excelWriter = EasyExcel.write(excelFilePath, Column.class)
                .build();
        try {
            List<Table> tables = tableMapper.findAllTablesMetadataByTableSchema(tableSchema);
            List<Column> columns;
            com.alibaba.excel.write.metadata.WriteSheet writeSheet;
            for (Table table : tables) {
                columns = columnMapper.findAllColumnsMetadataByTableSchemaAndTableName(tableSchema,
                        table.getTableName());
                writeSheet = EasyExcel.writerSheet(String.format("%s(%s)", table.getTableName(),
                        table.getTableComment()))
                        .build();
                excelWriter.write(columns, writeSheet);
            }
        } finally {
            if (excelWriter != null) {
                excelWriter.finish();
            }
        }
    }
}
