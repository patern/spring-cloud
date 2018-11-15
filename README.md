# 参考ason-spring-cloud --学习专用

## 项目简介
* ason-spring-cloud是微服务springcloud脚手架，整合了 mybatis-plus，redis，shiro。
* 使用Maven对项目进行模块化管理，提高项目的易开发性、扩展性。
* 系统包括分布式配置、eureka注册中心、服务中心等。
* 每个模块服务多系统部署，注册到同一个eureka集群服务注册中心，实现集群部署。

## 依赖
* Maven 3.5.0
* Java 8
* MySQL 5.7
* Redis 3.2.9

## 工程说明

```
ason-spring-cloud
├── common -- 公用模块（含实体类）。
├── db -- 数据操作模块
|    ├── db-mysql -- 集成mybatis-plus
|    ├── db-redis -- redis集成模块
|    └── db-cache -- 使用redis做缓存
├── eureka-server -- 注册中心(端口:1111)
├── config-server -- 配置中心(端口:7001)
├── rms-service -- 用户权限管理系统[未完成](端口:8888)
└── gateway  -- 服务网关
```

## 部署说明
 * 修改本地hosts文件，添加：
 
```
127.0.0.1  ason-eureka-server  
127.0.0.1  ason-hostname
```
 * 新建数据库rms_db，执行 rms-service/doc/rms_db.sql 导入数据  
   修改 ./config-server/config-repo/data-dev.yml 数据库的用户名和密码

 * 启动顺序：注册中心(1111)、配置中心端口(7001)、权限控制系统的微服务（8888）。

## 相关博文
##### db-mysql模块中对mybatis-plus的整合：  
[【springboot系列】springboot整合独立模块Druid + mybatis-plus](https://segmentfault.com/a/1190000011368822)

##### db-redis、db-cache模块中使用Redis作为内存缓存：  
[【springboot系列】springboot整合独立模块 redis 做缓存](https://segmentfault.com/a/1190000011371804)
