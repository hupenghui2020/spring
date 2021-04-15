package com.hph.demo.beanDefinitionRegistryPostProcessor;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author hph
 */
public class ApplicationTest {

	public static void main(String[] args) {

		AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext();
		ac.addBeanFactoryPostProcessor(new BeanDefinitionRegistryPostProcessorTest());
		ac.refresh();
	}
}
