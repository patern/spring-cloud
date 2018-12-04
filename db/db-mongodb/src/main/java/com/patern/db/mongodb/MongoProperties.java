package com.patern.db.mongodb;

import com.xiaoleilu.hutool.util.StrUtil;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

//@Configuration
@ConfigurationProperties(prefix = "spring.data.mongodb")
public class MongoProperties {
	
	@Value("${host}")
    private String host;
	@Value("${port}")
    private Integer port;
	@Value("${userName}")
    private String userName;
	@Value("${password}")
    private String password;
	@Value("${dbName}")
    private String dbName;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDbName() {
        return dbName;
    }

    public void setDbName(String dbName) {
        this.dbName = dbName;
    }

    @Override
    public String toString() {
        StringBuilder fullUrl = new StringBuilder("mongodb://");
        if (StrUtil.isNotBlank(userName) && StrUtil.isNotBlank(password)){
            fullUrl.append(userName).append(":").append(password).append("@");
        }
        fullUrl.append(host).append(":").append(port)
                .append("/").append(dbName).append("?socketTimeout=10000");
        return fullUrl.toString();
    }
}
