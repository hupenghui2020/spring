package com.hph.demo.annotationBeanDefinitionReader.scanner;

/**
 * 测试自定义扫描器
 * @author hph
 */
public class ApplicationTest {

	public static void main(String[] args) {

		CustomAnnotationConfigApplicationContext ac = new CustomAnnotationConfigApplicationContext();
		ac.register(DemoConfig.class);
		// 每次调用scan方法都是使用新的扫描器（比如扫描这个路径的是一个扫描器，而 @ComponentScan 注解扫描的又是另外一个新的扫描器）
		ac.scan("com.hph.demo.annotationBeanDefinitionReader.scanner.bean");
		ac.refresh();
	}
}
