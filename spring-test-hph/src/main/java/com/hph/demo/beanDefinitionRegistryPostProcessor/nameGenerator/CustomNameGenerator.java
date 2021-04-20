package com.hph.demo.beanDefinitionRegistryPostProcessor.nameGenerator;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanNameGenerator;

/**
 * 自定义名称生成策略（作为通过api设置使用）
 * @author hph
 */
public class CustomNameGenerator implements BeanNameGenerator {

	@Override
	public String generateBeanName(BeanDefinition definition, BeanDefinitionRegistry registry) {

		return generateBeanName(definition, registry);
	}
}
