package com.hph.demo.beanDefinitionRegistryPostProcessor;

import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 测试：当一个类实现了 BeanDefinitionRegistryPostProcessor和Ordered接口，
 * 	如果在postProcessBeanDefinitionRegistry方法中，注册了一个实现了BeanDefinitionRegistryPostProcessor和PriorityOrdered接口的类，
 * 	那么会怎么执行呢？
 * 	（结果：会被当成只实现了BeanDefinitionRegistryPostProcessor接口的类进行处理）
 * @author hph
 */
public class ApplicationTest {

	public static void main(String[] args) {

		AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext();
		RootBeanDefinition rbd = new RootBeanDefinition(OrderedBeanDefinitionRegistryPostProcessorTest.class);
		ac.registerBeanDefinition("aa", rbd);
		ac.refresh();
	}
}
