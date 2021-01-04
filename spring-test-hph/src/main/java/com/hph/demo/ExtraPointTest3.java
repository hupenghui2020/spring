package com.hph.demo;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.stereotype.Component;

/**
 * 测试扩展点1
 * 执行手动注册的实现 BeanDefinitionRegistryPostProcessor 接口的类的 postProcessBeanDefinitionRegistry 方法
 * @author hph
 */
@Component
public class ExtraPointTest3 implements BeanDefinitionRegistryPostProcessor {

	@Override
	public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {

		System.out.println("测试扩展点3: 执行其他程序员自定义并且实现 BeanDefinitionRegistryPostProcessor 接口并且没有实现Ordered接口的 postProcessBeanDefinitionRegistry 方法");
		System.out.println("测试扩展点3: " + registry.getBeanDefinitionCount());
	}

	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
	}
}
