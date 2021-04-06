package com.hph.demo.annotationBeanDefinitionReader.beanNameGenerator;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 测试自定义的 beanNameGenerator
 * （主要是测试两种不同的Scanner）
 * @author hph
 */
public class ApplicationTest {

	public static void main(String[] args) {

		AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext();
		ac.setBeanNameGenerator(new CustomBeanNameGenerator());
		ac.register(Aabdrbg.class, DemoConfig.class);
		ac.refresh();
	}
}
