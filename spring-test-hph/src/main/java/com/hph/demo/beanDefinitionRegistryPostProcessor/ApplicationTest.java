package com.hph.demo.beanDefinitionRegistryPostProcessor;

import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author hph
 */
public class ApplicationTest {

	public static void main(String[] args) {

		AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext();
		//ac.addBeanFactoryPostProcessor(new OrderedBeanDefinitionRegistryPostProcessorTest());
		RootBeanDefinition rbd = new RootBeanDefinition(OrderedBeanDefinitionRegistryPostProcessorTest.class);
		ac.registerBeanDefinition("bb", rbd);
		ac.refresh();
	}
}
