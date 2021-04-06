package com.hph.demo.annotationBeanDefinitionReader.beanNameGenerator;

import org.springframework.beans.factory.BeanNameAware;

public class Aabdrbg implements BeanNameAware {

	@Override
	public void setBeanName(String name) {

		System.out.println("Aabdrbg = " + name);
	}
}
