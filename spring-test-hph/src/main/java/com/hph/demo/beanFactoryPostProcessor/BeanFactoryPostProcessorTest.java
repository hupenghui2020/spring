package com.hph.demo.beanFactoryPostProcessor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.context.annotation.ScannedGenericBeanDefinition;
import org.springframework.stereotype.Component;

/**
 * 测试 BeanFactoryPostProcessor 的扩展功能
 * @author hph
 */
@Component
public class BeanFactoryPostProcessorTest implements BeanFactoryPostProcessor {

	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

		ScannedGenericBeanDefinition sb = (ScannedGenericBeanDefinition)beanFactory.getBeanDefinition("abfpp");
		// 通过名称进行自动注入（通过方法名称获取需要注入的依赖，然后通过反射传给set方法参数）
		 sb.setAutowireMode(AbstractBeanDefinition.AUTOWIRE_BY_NAME);
		// 通过类型进行自动注入（通过set方法参数的类型进行注入，和方法名无关）
		// sb.setAutowireMode(AbstractBeanDefinition.AUTOWIRE_BY_TYPE);
	}
}
