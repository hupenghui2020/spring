package com.hph.demo.beanFactoryPostProcessor.freezeConfiguration;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author hph
 */
public class ApplicationTest {

	public static void main(String[] args) {

		AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext();
		ac.register(DemoConfig.class);
		// 直接将实例对象注册到singletonObjects中
		// ac.getBeanFactory().registerSingleton("d", new D());
		ac.refresh();
		ac.getBean(Abfpp.class);
	}
}
