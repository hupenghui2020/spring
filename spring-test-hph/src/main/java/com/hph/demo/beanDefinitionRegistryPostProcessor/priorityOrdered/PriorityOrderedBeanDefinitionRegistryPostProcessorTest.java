package com.hph.demo.beanDefinitionRegistryPostProcessor.priorityOrdered;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.core.Ordered;
import org.springframework.core.PriorityOrdered;

/**
 * 测试：当一个类实现了 BeanDefinitionRegistryPostProcessor和Ordered接口，
 * 	如果在postProcessBeanDefinitionRegistry方法中，注册了一个实现了BeanDefinitionRegistryPostProcessor和PriorityOrdered接口的类，
 * 	那么会怎么执行呢？
 * 	（结果：会被当成只实现了BeanDefinitionRegistryPostProcessor接口的类进行处理）
 * @author hph
 */
public class PriorityOrderedBeanDefinitionRegistryPostProcessorTest implements BeanDefinitionRegistryPostProcessor, PriorityOrdered {

	@Override
	public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {

		System.out.println("aaaaaaaaaaaaaaaaaaaaa");
	}

	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

		System.out.println("bbbbbbbbbbbbbbbb");
	}

	@Override
	public int getOrder() {
		return 0;
	}
}
