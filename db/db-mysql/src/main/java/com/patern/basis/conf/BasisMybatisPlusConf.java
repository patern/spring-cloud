package com.patern.basis.conf;

import com.alibaba.druid.pool.DruidDataSource;
import com.patern.utils.BlankUtil;
import com.baomidou.mybatisplus.entity.GlobalConfiguration;
import com.baomidou.mybatisplus.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.spring.MybatisSqlSessionFactoryBean;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

/**
 * Created by patern on 2017/8/15.
 */
@Configuration
public class BasisMybatisPlusConf {

	private static final Log log = LogFactory.getLog(BasisMybatisPlusConf.class);

	//    mybatisPlus全局配置
	@Bean(name = "basisGlobalConfig")
	public GlobalConfiguration globalConfig(
			@Value("${mybatisPlus.globalConfig.idType}") Integer idType, //主键类型  0:"数据库ID自增", 1:"用户输入ID",2:"全局唯一ID (数字类型唯一ID)", 3:"全局唯一ID UUID";
			@Value("${mybatisPlus.globalConfig.dbColumnUnderline}") Boolean dbColumnUnderline, //驼峰下划线转换
			@Value("${mybatisPlus.globalConfig.isCapitalMode}") Boolean isCapitalMode //数据库大写下划线转换
	) {
		log.info("初始化GlobalConfiguration");
		GlobalConfiguration globalConfig = new GlobalConfiguration();
		// 主键类型
		if ( !BlankUtil.isBlank(idType)) {
			globalConfig.setIdType(idType);
		}
		// 驼峰下划线转换
		if ( !BlankUtil.isBlank(dbColumnUnderline)) {
			globalConfig.setDbColumnUnderline(dbColumnUnderline);
		}
		// 数据库大写下划线转换
		if ( !BlankUtil.isBlank(isCapitalMode)) {
			globalConfig.setCapitalMode(isCapitalMode);
		}
		return globalConfig;
	}

	@Bean(name = "basisSqlSessionFactory")
	public SqlSessionFactory sqlSessionFactory(@Qualifier(value = "basisGlobalConfig")GlobalConfiguration globalConfig,
											   @Qualifier(value = "basisDataSource")DruidDataSource dataSource) throws Exception {
		log.info("初始化SqlSessionFactory");
		String mapperLocations = "classpath:db-patern/sql/**/*.xml";
		String configLocation = "classpath:db-patern/mybatis/mybatis-sqlconfig.xml";
		String typeAliasesPackage = "com.patern.entity.**";
		MybatisSqlSessionFactoryBean sqlSessionFactory = new MybatisSqlSessionFactoryBean();
		// 数据源
		sqlSessionFactory.setDataSource(dataSource);
		// 全局配置
		sqlSessionFactory.setGlobalConfig(globalConfig);
		Interceptor[] interceptor = {new PaginationInterceptor()};
		// 分页插件
		sqlSessionFactory.setPlugins(interceptor);
		ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
		try {
			// 自动扫描Mapping.xml文件
			sqlSessionFactory.setMapperLocations(resolver.getResources(mapperLocations));
			sqlSessionFactory.setConfigLocation(resolver.getResource(configLocation));
			sqlSessionFactory.setTypeAliasesPackage(typeAliasesPackage);
			return sqlSessionFactory.getObject();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	//    MyBatis 动态扫描
	@Bean(name = "basisMapperScannerConfigurer")
	public MapperScannerConfigurer mapperScannerConfigurer() {
		log.info("初始化basisMapperScannerConfigurer");
		MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
		String basePackage = "com.patern.db.mapper";
		mapperScannerConfigurer.setBasePackage(basePackage);
		return mapperScannerConfigurer;
	}

	//    配置事务管理
	@Bean(name = "basisTransactionManager")
	public DataSourceTransactionManager transactionManager(@Qualifier(value = "basisDataSource")DruidDataSource dataSource) {
		log.info("初始化basisTransactionManager");
		return new DataSourceTransactionManager(dataSource);
	}
}
