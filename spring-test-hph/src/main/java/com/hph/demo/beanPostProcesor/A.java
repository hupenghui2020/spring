package com.hph.demo.beanPostProcesor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

public class A implements BeanPostProcessor {

	public A() {

		System.out.println("A 被创建了........");
	}

	public void test(){

		System.out.println("获取A实例成功。。。。");
	}

	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {

		System.out.println("11111111111111111111");
		return bean;
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {

		System.out.println("2222222222222222222222");
		return bean;
	}
}
