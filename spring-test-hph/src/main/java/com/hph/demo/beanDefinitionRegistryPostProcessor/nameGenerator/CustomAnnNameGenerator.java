package com.hph.demo.beanDefinitionRegistryPostProcessor.nameGenerator;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanNameGenerator;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * 自定义名称生成策略（作为注解的属性使用）
 * @author hph
 */
@Component
public class CustomAnnNameGenerator implements BeanNameGenerator {

	@Override
	public String generateBeanName(BeanDefinition definition, BeanDefinitionRegistry registry) {

		return UUID.randomUUID().toString();
	}
}
