package com.hph.demo.annotationBeanDefinitionReader.scanner;

/**
 * 测试自定义扫描器
 * @author hph
 */
public class ApplicationTest {

	public static void main(String[] args) {

		CustomAnnotationConfigApplicationContext ac = new CustomAnnotationConfigApplicationContext();
		ac.register(DemoConfig.class);
		ac.scan("com.hph.demo.annotationBeanDefinitionReader.scanner.bean");
		ac.refresh();
	}
}
