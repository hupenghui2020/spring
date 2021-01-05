package com.hph.demo;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

/**
 * 测试扩展点2
 * 测试是否是在执行 ConfigurationClassPostProcessor 类的地方执行的
 * @author hph
 */

@Component
public class ExtraPointTest2 implements BeanDefinitionRegistryPostProcessor, Ordered {

	@Override
	public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {

		System.out.println("ExtraPointTest2 postProcessBeanDefinitionRegistry");
	}

	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

		System.out.println("ExtraPointTest2 postProcessBeanFactory");
	}


	@Override
	public int getOrder() {
		return 100;
	}
}
