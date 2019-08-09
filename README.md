## learnxd

## 资料
[Spring文档](https://spring.io/guides/gs/serving-web-content/)
[github工程文档](https://github.com/aihuidezo/learnxd)
[bootstrap文档](https://v3.bootcss.com/getting-started/)
[Spring官方文档](https://docs.spring.io/spring-boot/docs/2.0.0.RC1/reference/html/boot-features-sql.html#boot-features-embedded-database-support)
## 工具
[git工具](https://git-scm.com)



## sql脚本
```sql
    user 表
CREATE TABLE `t_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `account_id` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `token` char(36) DEFAULT NULL,
  `gmt_create` bigint(20) DEFAULT NULL,
  `gmt_modified` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci
```
```bash
 mvn flyway:migrate 
```

## flyway mysql 相关引用配置
```mxml
<build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
            </plugin>
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
        </plugins>
    </build>
```



