## learnxd
## 资料
[Spring文档](https://spring.io/guides/gs/serving-web-content/)
[github工程文档](https://github.com/aihuidezo/learnxd)
[bootstrap文档](https://v3.bootcss.com/getting-started/)
[Spring官方文档](https://docs.spring.io/spring-boot/docs/2.0.0.RC1/reference/html/boot-features-sql.html#boot-features-embedded-database-support)
[菜鸟教程(Mysql)](https://www.runoob.com/mysql/mysql-tutorial.html)
[thymeleaf](https://www.thymeleaf.org/doc/tutorials/3.0/usingthymeleaf.html)
## 工具
[IDEA](http://www.jetbrains.com/idea/download/#section=windows)
[git工具](https://git-scm.com)
[MySQl](https://dev.mysql.com/downloads/)
[flyway工具](https://flywaydb.org/)
[lombok工具](https://projectlombok.org/)
## sql脚本
```sql
create table `t_user` (
  `id` int(11) not null auto_increment,
  `account_id` varchar(100) character set utf8mb4 collate utf8mb4_0900_ai_ci default null,
  `name` varchar(50) character set utf8mb4 collate utf8mb4_0900_ai_ci default null,
  `token` char(36) default null,
  `gmt_create` bigint(20) default null,
  `gmt_modified` bigint(20) default null,
  `bio` varchar(256) null,
  `avatar_url` varchar(100) null,
  primary key (`id`)
) engine=innodb auto_increment=7 default charset=utf8mb4 collate=utf8mb4_0900_ai_ci
```
```sql
create table t_question
(
    id int auto_increment,
    title varchar(50) null,
    description text null,
    gmt_create bigint null,
    gmt_modified bigint null,
    creator int null,
    comment_count int default 0 null,
    view_count int default 0 null,
    like_count int default 0 null,
    tag varchar(256) null,
    constraint t_question_pk
        primary key (id)
);
```
## flyway mysql 相关引用配置
```mxml
<plugin>
    <groupId>org.flywaydb</groupId>
    <artifactId>flyway-maven-plugin</artifactId>
    <version>5.2.4</version>
    <configuration>
        <url>jdbc:mysql://localhost/learnxd?serverTimezone=GMT%2B8</url>
        <user>root</user>
        <password>zo203</password>
    </configuration>
    <dependencies>
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <version>8.0.11</version>
        </dependency>
    </dependencies>
</plugin>
```
```bash
 mvn flyway:migrate 
```
## lombok依赖
```mxml
<dependency>
    <groupId>org.projectlombok</groupId>
    <artifactId>lombok</artifactId>
    <version>1.18.8</version>
    <scope>provided</scope>
</dependency>
```



