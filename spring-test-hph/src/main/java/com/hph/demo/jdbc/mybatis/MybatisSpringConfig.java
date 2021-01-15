package com.hph.demo.jdbc.mybatis;

import com.hph.demo.jdbc.JdbcConfig;
import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.io.ClassPathResource;
import javax.sql.DataSource;

/**
 * @author hph
 */
@Configuration
@Import(JdbcConfig.class)
public class MybatisSpringConfig {

	@Bean
	public SqlSessionFactoryBean sqlSessionFactory(DataSource dataSource){

		SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean();
		sqlSessionFactory.setConfigLocation(new ClassPathResource("com/hph/demo/jdbc/mybatis/mybatis-configuration.xml"));
		sqlSessionFactory.setDataSource(dataSource);
		return sqlSessionFactory;
	}

	@Bean
	public MapperFactoryBean userMapper(SqlSessionFactoryBean sqlSessionFactory) throws Exception {

		MapperFactoryBean mapperFactoryBean = new MapperFactoryBean();
		mapperFactoryBean.setMapperInterface(UserMapper.class);
		mapperFactoryBean.setSqlSessionFactory(sqlSessionFactory.getObject());
		return mapperFactoryBean;
	}
}
