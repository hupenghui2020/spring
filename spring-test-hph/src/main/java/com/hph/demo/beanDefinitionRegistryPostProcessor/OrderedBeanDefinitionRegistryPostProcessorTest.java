package com.hph.demo.beanDefinitionRegistryPostProcessor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.core.Ordered;

/**
 * @author hph
 */
public class OrderedBeanDefinitionRegistryPostProcessorTest implements BeanDefinitionRegistryPostProcessor, Ordered {

	@Override
	public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {

		System.out.println("注册一个实现了BeanDefinitionRegistryPostProcessor和PriorityOrdered接口类");
		RootBeanDefinition rbd = new RootBeanDefinition(PriorityOrderedBeanDefinitionRegistryPostProcessorTest.class);
		registry.registerBeanDefinition("aa", rbd);
	}

	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

		System.out.println("执行 postProcessBeanFactory 方法");
	}

	@Override
	public int getOrder() {
		return 0;
	}
}
