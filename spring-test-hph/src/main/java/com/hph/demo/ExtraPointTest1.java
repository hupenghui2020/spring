package com.hph.demo;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;

/**
 * 测试扩展点1
 * 执行手动注册的实现 BeanDefinitionRegistryPostProcessor 接口的类的 postProcessBeanDefinitionRegistry 方法
 * @author hph
 */
public class ExtraPointTest1 implements BeanDefinitionRegistryPostProcessor {

	@Override
	public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {

		System.out.println("测试扩展点1: 执行手动注册的实现 BeanDefinitionRegistryPostProcessor 接口的类的 postProcessBeanDefinitionRegistry 方法");
		System.out.println("测试扩展点1: " + registry.getBeanDefinitionCount());
	}

	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
	}
}
