package com.hph.demo;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanClassLoaderAware;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.stereotype.Component;

/**
 * 扩展点9
 * 通过实现 BeanClassLoaderAware，可以拿到 BeanFactory
 * @author hph
 */
@Component
public class ExtraPointTest9 implements BeanFactoryAware {

	@Override
	public void setBeanFactory(BeanFactory beanFactory) throws BeansException {

		System.out.println("ExtraPointTest9 setBeanFactory");
	}
}
