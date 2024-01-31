# temp_springBoot_vue
springBoot 和 Vue

### 接口说明

测试接口

```html
全部是Get方式访问
http://localhost:8071/api/
http://localhost:8071/api/test/detail
http://localhost:8071/api/user/detail
```

### 结构文件说明

```wiki
com/example/fei/common/config
这里面的文件配置了  @Configuration, 在启动器FeiApplication.java会扫描package com.example.fei 下带@Configuration的文件,这样配置文件就会自动扫描到了
```

### 自动生成`mapper`

```wiki
#src/main/resources/generatorConfig.xml 在这个文件中

schema属性填你的数据库名称
catalog属性填你的数据库名称
tableName填你的表名称,
domainObjectName属性填你的要生成的java的类名

注意 classPathEntry 的路径根据自己的系统环境配置

#使用
配好表后在pom.xml目录下执行 mvn mybatis-generator:generate
自动生成的三个文件有:
01) resources/mapper/xxx.xml
02) src/main/java/com/example/fei/mapper/xxx.java
03) src/main/java/com/example/fei/domain/xxx.java
```



### 库说明

1. hutool-all 工具包

   > Hutool是一个小而全的Java工具类库，通过静态方法封装
   >
   > ```xml
   > <dependency>
   >     <groupId>cn.hutool</groupId>
   >     <artifactId>hutool-all</artifactId>
   >     <version>5.8.16</version>
   > </dependency>
   > ```
   >
   > 
   >
   > 

2. xxx

3. xxx
