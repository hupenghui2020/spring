package com.hph.demo.importBeanDefinitionRegistrar;

import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @author hph
 */
public class ImportBeanDefinitionRegistrarTest implements ImportBeanDefinitionRegistrar {

	@Override
	public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {

		AbstractBeanDefinition beanDefinition = BeanDefinitionBuilder.genericBeanDefinition(Eibdr.class).getBeanDefinition();
		registry.registerBeanDefinition("e", beanDefinition);
		// 测试如果有两个bean 名称相同，使用两种注册方式进行注册进去（一种是添加到beanDefinitionMap走生命周期进行注册，还有一种直接调用ac.getBeanFactory().registerSingleton("d", new D());直接放入singletonObjects容器进行注册）,
		// singletonObjects容器中到底是哪种类型的bean，测试结果为F类型的，因为下面的方法是在refresh中执行的，因为名称相同，会先删除覆盖了调用ac.getBeanFactory().registerSingleton("d", new D());方法而提前注册的bean，然后把这个bean注册进去
		//registry.registerBeanDefinition("d", BeanDefinitionBuilder.genericBeanDefinition(Fibdr.class).getBeanDefinition());
	}
}
