package com.patern.basis.conf;


import com.patern.DataSourceConnectionProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
/**
 * Created by patern on 2017/10/31.
 */
@Configuration
@ConfigurationProperties(prefix = "basis.connection")
public class BasisDataSourceConnectionProperties extends DataSourceConnectionProperties {

}
