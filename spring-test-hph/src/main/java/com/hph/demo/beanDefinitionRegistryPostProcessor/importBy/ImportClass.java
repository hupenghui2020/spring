package com.hph.demo.beanDefinitionRegistryPostProcessor.importBy;

import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @author hph
 */
@Configuration
public class ImportClass {

	/**
	 * 这里的 @Bean 方法不会被解析，因为实现了ImportBeanDefinitionRegistrar的话，ImportClass不会被解析
	 * @return
	 */
	@Bean
	public BeanMethodTest beanMethodTest() {

		return new BeanMethodTest();
	}

	@Bean
	public BeanMethodTest2 beanMethodTest2() {

		beanMethodTest();
		return new BeanMethodTest2();
	}
}
