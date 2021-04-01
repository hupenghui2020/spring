package com.hph.demo.importBeanDefinitionRegistrar;

import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author hph
 */
public class ApplicationTest {

	public static void main(String[] args) {

		AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext();
		ac.register(DemoConfig.class);
		ac.registerBeanDefinition("d", BeanDefinitionBuilder.genericBeanDefinition(Fibdr.class).getBeanDefinition());
		// 直接将实例对象注册到singletonObjects中
		ac.getBeanFactory().registerSingleton("d", new Dibdr());
		ac.refresh();
		ac.getBean(Eibdr.class).print();
	}
}
