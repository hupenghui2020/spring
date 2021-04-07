package com.hph.demo.annotationBeanDefinitionReader.scanner;

import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.core.type.filter.AnnotationTypeFilter;

/**
 * 自定义扫描器，可以扫描加了自定义注解的类
 * @author hph
 */
public class CustomClassPathBeanDefinitionScanner extends ClassPathBeanDefinitionScanner {

	public CustomClassPathBeanDefinitionScanner(BeanDefinitionRegistry registry) {
		// 为什么调用这个父类构造方法（因为这个构造方法里可以对扫描的class进行过滤）
		super(registry);
	}

	/**
	 * 覆盖父类的扫描规则，给这个扫描器自定义注解类的过滤
	 */
	@Override
	protected void registerDefaultFilters() {

		addIncludeFilter(new AnnotationTypeFilter(CustomBean.class));
	}
}
