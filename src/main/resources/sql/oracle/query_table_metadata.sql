-- 查询指定用户下的所有表信息（包含表名称，表注释）
select table_name as tableName,
    owner as owner,
    comments as tableComment
from all_tab_comments
where table_type = 'TABLE'
and owner = 'QT_PAS';
-- 查询指定表的字段信息（包含所属表名称、字段名称、字段类型、字段长度、字段注释、是否允许为空、是否为主键、数值精度、是否为外键）
select t1.table_name as tableName,
    t1.column_name as columnName,
    data_type as dataType,
    data_length as dataLength,
    comments as columnComment,
    case 
        when nullable = 'Y' then 'YES'
        when nullable = 'N' then 'NO'
        else 'NO' 
    end as isNullable,
    t1.data_default as dataDefault,
    case 
        when t3.column_name is null then 'NO'
        else 'YES' 
    end as isPrimaryKey ,
    data_scale as dataScale,
    case 
        when t4.column_name is null then 'NO'
        else 'YES' 
    end as isForeignKey,
    case 
        when t5.column_name is null then 'NO'
        else 'YES' 
    end as isUnique
from user_col_comments t2 
left join user_tab_cols t1 on t1.column_name = t2.column_name and t1.table_name = t2.table_name
left join (
select col.column_name as column_name,con.table_name as table_name from user_constraints con,user_cons_columns col
where con.constraint_name = col.constraint_name 
and con.constraint_type = 'P'
) t3
on t3.table_name = t1.table_name and t1.column_name = t3.column_name
left join (
select col.column_name as column_name,con.table_name as table_name from user_constraints con,user_cons_columns col
where con.constraint_name = col.constraint_name 
and con.constraint_type = 'R'
) t4
on t4.table_name = t1.table_name and t1.column_name = t4.column_name
left join (
select col.column_name as column_name,con.table_name as table_name from user_constraints con,user_cons_columns col
where con.constraint_name = col.constraint_name 
and con.constraint_type = 'U'
) t5
on t5.table_name = t1.table_name and t1.column_name = t5.column_name
where t1.table_name = 'SYS_USER_ROLE';

desc USER_TAB_COLUMNS;

select * from USER_TAB_COLUMNS where table_name = 'ACCESS_LOG';
