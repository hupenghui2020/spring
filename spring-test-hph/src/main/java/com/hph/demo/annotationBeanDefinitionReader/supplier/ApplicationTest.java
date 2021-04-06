package com.hph.demo.annotationBeanDefinitionReader.supplier;

import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 测试supplier的用法，spring通过静态工厂方法创建bean的方式
 * @author hph
 */
public class ApplicationTest {

	public static void main(String[] args) {

		AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext();
		ac.register(DemoConfig.class);
		RootBeanDefinition rb = new RootBeanDefinition(Aabdrs.class);
		rb.setInstanceSupplier(Cabdrs::createA);
		ac.registerBeanDefinition("aaa", rb);
		ac.refresh();
	}
}
