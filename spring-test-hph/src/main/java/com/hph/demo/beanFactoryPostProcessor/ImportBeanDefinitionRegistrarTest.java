package com.hph.demo.beanFactoryPostProcessor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.context.annotation.ScannedGenericBeanDefinition;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.stereotype.Component;

/**
 * @author hph
 */
public class ImportBeanDefinitionRegistrarTest implements ImportBeanDefinitionRegistrar {

	@Override
	public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {

		AbstractBeanDefinition beanDefinition = BeanDefinitionBuilder.genericBeanDefinition(E.class).getBeanDefinition();
		registry.registerBeanDefinition("e", beanDefinition);
	}
}
