package com.hph.demo.beanDefinitionRegistryPostProcessor.nameGenerator;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Import;

/**
 * 测试不同方式提供的 GeneratorName 哪个会生效
 * 	1、通过api提供的
 * 	2、通过 @ComponentScan 注解提供的
 * 	（结果：通过api提供的生效）
 * @author hph
 */
public class ApplicationTest {

	public static void main(String[] args) {

		AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext();
		ac.register(ComponentScanTest.class);
		// 通过api方式设置 BeanNameGenerator
		ac.setBeanNameGenerator(new CustomNameGenerator());
		ac.refresh();
	}
}
