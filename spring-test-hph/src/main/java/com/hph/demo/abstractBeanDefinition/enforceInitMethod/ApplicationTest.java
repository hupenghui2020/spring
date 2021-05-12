package com.hph.demo.abstractBeanDefinition.enforceInitMethod;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 测试 AbstractBeanDefinition 的 EnforceInitMethod 属性的作用，具体解析看:
 * @see AbstractBeanDefinition#enforceInitMethod
 * @author hph
 */
public class ApplicationTest {

	public static void main(String[] args) {

		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
		applicationContext.register(A.class);
		GenericBeanDefinition genericBeanDefinition = (GenericBeanDefinition)applicationContext.getBeanDefinition("a");
		genericBeanDefinition.setInitMethodName("init");
		genericBeanDefinition.setNonPublicAccessAllowed(false);
		genericBeanDefinition.setEnforceInitMethod(false);
		applicationContext.refresh();
	}
}
