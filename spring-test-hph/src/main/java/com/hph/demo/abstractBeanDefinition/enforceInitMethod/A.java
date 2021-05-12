package com.hph.demo.abstractBeanDefinition.enforceInitMethod;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class A {

	private void init(){

		System.out.println("init...........");
	}


	@PreDestroy
	public void initAnn() {

		System.out.println("initAnn......");
	}
}
