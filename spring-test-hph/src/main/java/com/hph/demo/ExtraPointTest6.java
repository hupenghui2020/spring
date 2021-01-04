package com.hph.demo;

import org.springframework.beans.BeansException;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * 扩展点6：
 * 通过构造函数或工厂方法在实例化 bean 之后但在发生 Spring 属性填充（通过显式属性或自动装配）之前执行操作
 *
 * @author hph
 */
@Component
public class ExtraPointTest6 implements InstantiationAwareBeanPostProcessor {

	/**
     * 扩展点6
	 * @param pvs the property values that the factory is about to apply (never {@code null})
     * @param bean the bean instance created, but whose properties have not yet been set
     * @param beanName the name of the bean
     * @return
     * @throws BeansException
	 */
	@Override
	public PropertyValues postProcessProperties(PropertyValues pvs, Object bean, String beanName) throws BeansException {

		if(bean instanceof A) {
			System.out.println("到我修改属性了！！！！！！！！！！！");
		}
		return pvs;
	}
}
