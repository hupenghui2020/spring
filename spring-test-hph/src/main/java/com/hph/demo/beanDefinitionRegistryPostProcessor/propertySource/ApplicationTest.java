package com.hph.demo.beanDefinitionRegistryPostProcessor.propertySource;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 测试设置两个配置文件路径是否会有两个PropertySource 对象进行存储
 * （结果：是的）
 * @author hph
 */
public class ApplicationTest {

	public static void main(String[] args) {

		AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext();
		ac.register(PropertyTest.class);
		ac.refresh();
	}
}
