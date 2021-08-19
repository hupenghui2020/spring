package com.hph.demo.jdbc.mybatis;

import com.hph.demo.jdbc.JdbcConfig;
import com.hph.demo.jdbc.mybatis.mapper.UserMapper;
import org.apache.commons.dbcp2.BasicDataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperFactoryBean;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.config.TransactionManagementConfigUtils;

import javax.sql.DataSource;

/**
 * @author hph
 */
@Configuration
@Import(JdbcConfig.class)
public class MybatisSpringConfig {

	/**
	 * 相当于提供sql操作的代理对象
	 * 解析xml文件，将mapper.xml解析到 Configuration 中
	 * @param dataSource
	 * @return
	 */
	@Bean
	public SqlSessionFactoryBean sqlSessionFactory(BasicDataSource dataSource){

		SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean();
		sqlSessionFactory.setConfigLocation(new ClassPathResource("mybatis-configuration.xml"));
		sqlSessionFactory.setDataSource(dataSource);
		return sqlSessionFactory;
	}

	/**
	 * 相当于提供sql操作的代理接口（如果有多个mapper的话需要一个一个添加，下面使用MapperScannerConfigurer来扫描mapper）
	 * @param sqlSessionFactory
	 * @return
	 * @throws Exception
	 */
	/*@Bean
	public MapperFactoryBean userMapper(SqlSessionFactoryBean sqlSessionFactory) throws Exception {

		MapperFactoryBean mapperFactoryBean = new MapperFactoryBean();
		mapperFactoryBean.setMapperInterface(UserMapper.class);
		mapperFactoryBean.setSqlSessionFactory(sqlSessionFactory.getObject());
		return mapperFactoryBean;
	}*/

	/**
	 * 指定包进行扫描
	 * @return
	 */
	@Bean
	public MapperScannerConfigurer mapperScannerConfigurer(){

		MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
		// 提前将包里的类进行注册，放进 beanDefinitionsMap 容器中
		mapperScannerConfigurer.setBasePackage("com.hph.demo.jdbc.mybatis.mapper");
		return mapperScannerConfigurer;
	}

	/**
	 * mybatis支持spring的事务
	 * @param dataSource
	 * @return
	 */
	@Bean
	public DataSourceTransactionManager transactionManager(DataSource dataSource){

		DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager();
		dataSourceTransactionManager.setDataSource(dataSource);
		return dataSourceTransactionManager;
	}
}
