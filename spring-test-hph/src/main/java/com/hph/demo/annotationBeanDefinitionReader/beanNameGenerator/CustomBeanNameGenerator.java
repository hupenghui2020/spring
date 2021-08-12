package com.hph.demo.annotationBeanDefinitionReader.beanNameGenerator;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanNameGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.Random;

/**
 * 自定义beanName 生成策略
 * @author hph
 */
public class CustomBeanNameGenerator implements BeanNameGenerator {

	@Override
	public String generateBeanName(BeanDefinition definition, BeanDefinitionRegistry registry) {

		return new Random().nextInt(20) + "xxx";
	}
}
