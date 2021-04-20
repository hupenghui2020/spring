package com.hph.demo.beanDefinitionRegistryPostProcessor.importBy;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 测试： ConfigurationClass 类中存放的 importedBy 属性是什么
 * 	存放的类
 * 	 1、如果一个配置里面有多个内部类，且内部类为配置类，则 importedBy 存放的是外部类
 * @author hph
 */
public class ApplicationTest {

	public static void main(String[] args) {

		AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext();
		ac.register(OutClassTest.class);
		ac.refresh();
	}
}
