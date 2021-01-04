package com.hph.demo;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.beans.factory.wiring.BeanWiringInfo;
import org.springframework.stereotype.Component;

/**
 * 测试执行的顺序
 * @author hph
 */
@Component
public class MyDemoBeanFactoryPostProcessor implements BeanFactoryPostProcessor {

	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

		/*GenericBeanDefinition h = (GenericBeanDefinition)
				beanFactory.getBeanDefinition("h");
		GenericBeanDefinition z = (GenericBeanDefinition)
				beanFactory.getBeanDefinition("z");
		System.out.println("h mode="+h.getAutowireMode());
		System.out.println("z mode="+z.getAutowireMode());
		 h.setAutowireMode(AbstractBeanDefinition.AUTOWIRE_BY_NAME);
		 z.setAutowireMode(AbstractBeanDefinition.AUTOWIRE_BY_NAME);
		System.out.println("h mode="+h.getAutowireMode());
		System.out.println("z mode="+z.getAutowireMode());*/
	}
}
