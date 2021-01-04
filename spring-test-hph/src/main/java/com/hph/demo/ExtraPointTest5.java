package com.hph.demo;

import org.springframework.beans.BeansException;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.beans.factory.support.MergedBeanDefinitionPostProcessor;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.stereotype.Component;

/**
 * 扩展点5：
 * 通过构造函数或工厂方法在实例化 bean 之后但在发生 Spring 属性填充（通过显式属性或自动装配）之前执行操作
 *
 * @author hph
 */
@Component
public class ExtraPointTest5 implements InstantiationAwareBeanPostProcessor {

	@Override
	public boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {

		// 如果返回 false，说明不需要属性填充了，直接返回（默认是true）
		if(bean instanceof A) {
			System.out.println("到我了！！！！！！！！！！！");
		}
		return true;
	}
}
