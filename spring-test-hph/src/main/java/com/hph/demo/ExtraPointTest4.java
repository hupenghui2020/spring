package com.hph.demo;

import org.springframework.beans.factory.support.MergedBeanDefinitionPostProcessor;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.stereotype.Component;

/**
 * 扩展点4
 * 提供修改合并了的beanDefinition的机会
 * @author hph
 */
@Component
public class ExtraPointTest4 implements MergedBeanDefinitionPostProcessor {

	@Override
	public void postProcessMergedBeanDefinition(RootBeanDefinition beanDefinition, Class<?> beanType, String beanName) {
		System.out.println("ExtraPointTest4 postProcessMergedBeanDefinition");
	}
}
