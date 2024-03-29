# data-dict-build-tool
数据库数据字典生成工具，支持mysql和oracle的数据字典导出

## 使用方法

1. 修改配置文件中的MySQL数据库连接信息：用户名和密码

   ![image-20210716120952868](assets/images/image-20210716120952868.png)

说明，数据库帐号密码采用jasypt加密，参考资料：https://github.com/ulisesbocchio/jasypt-spring-boot

2. 修改需要导出数据字典的数据库类型

   ![image-20210716120911912](assets/images/image-20210716120911912.png)

3. 修改需要导出数据库的名称

   1. 数据库类型为mysql时，修改app.table-schema为需要导出数据字典的数据库名称

      ![image-20210716121128473](assets/images/image-20210716121128473.png)

   2. 数据库类型为oracle时，修改app.owner为oracle表空间所属的用户名

   ![image-20210716121150977](assets/images/image-20210716121150977.png)

4. 修改数据字典文件导出路径，默认放在D盘

![image-20210716121217698](assets/images/image-20210716121217698.png)

5. 运行程序即可

![image-20210716121248714](assets/images/image-20210716121248714.png)

