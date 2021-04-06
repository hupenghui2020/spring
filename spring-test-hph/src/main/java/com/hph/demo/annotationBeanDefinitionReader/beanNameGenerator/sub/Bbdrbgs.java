package com.hph.demo.annotationBeanDefinitionReader.beanNameGenerator.sub;

import org.springframework.beans.factory.BeanNameAware;
import org.springframework.stereotype.Component;

@Component
public class Bbdrbgs implements BeanNameAware {

	@Override
	public void setBeanName(String name) {

		System.out.println("Bbdrbgs = " + name);
	}
}
