package com.hph.demo.annotationBeanDefinitionReader.scanner;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 自定义 AnnotationConfigApplicationContext，
 * 为了重写scan方法，从而使用自定义的扫描器
 * @author hph
 */
public class CustomAnnotationConfigApplicationContext extends AnnotationConfigApplicationContext {

	private CustomClassPathBeanDefinitionScanner customClassPathBeanDefinitionScanner = new CustomClassPathBeanDefinitionScanner(this);

	@Override
	public void scan(String... basePackages) {

		customClassPathBeanDefinitionScanner.scan(basePackages);
	}
}
