##小白的第一天
##资料网站等
spring文档:https://spring.io/guides/gs/serving-web-content/
maven依赖包下载:https://mvnrepository.com/artifact/com.alibaba/fastjson/1.2.60

##总结
关于maven下载慢的解决方案:https://blog.csdn.net/qq1501340219/article/details/54638158
改bug:https://blog.csdn.net/Cjiaocsda1127/article/details/85044224
##脚本
```sql
create table HUBOY.H_USER
(
	ID int default  auto_increment,
	ACCOUNT_ID VARCHAR(100),
	NAME VARCHAR(50),
	TOKEN VARCHAR(36),
	GMT_CREATE BIGINT,
	GMT_MODIFIED BIGINT,
	constraint H_USER_PK
	primary key (ID)
);
```