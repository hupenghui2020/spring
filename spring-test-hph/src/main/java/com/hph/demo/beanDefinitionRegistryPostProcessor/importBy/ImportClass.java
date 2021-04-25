package com.hph.demo.beanDefinitionRegistryPostProcessor.importBy;

import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @author hph
 */
@Configuration
public class ImportClass implements ImportBeanDefinitionRegistrar {

	@Bean
	public BeanMethodTest beanMethodTest() {

		return new BeanMethodTest();
	}

	@Bean
	public BeanMethodTest2 beanMethodTest2() {

		beanMethodTest();
		return new BeanMethodTest2();
	}

	@Override
	public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {

	}
}
