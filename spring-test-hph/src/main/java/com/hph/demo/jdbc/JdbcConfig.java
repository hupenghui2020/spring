package com.hph.demo.jdbc;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author hph
 */
@Configuration
public class JdbcConfig {

	@Bean(destroyMethod = "close")
	public BasicDataSource dataSource(){

		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://127.0.0.1:3306/test?useUnicode=true&characterEncoding=UTF-8&autoReconnect=true&serverTimezone=GMT%2B8");
		dataSource.setUsername("root");
		dataSource.setPassword("123456");
		return dataSource;
	}
}
