package com.hph.demo;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author hph
 */
@Component("ix")
public class X implements ApplicationContextAware, BeanNameAware, InitializingBean {

	public X(){
		System.out.println("xxxx");
	}

	@PostConstruct
	public void init(){
		System.out.println("init xxxxxxxxxxx");
	}

	@Override
	public void afterPropertiesSet() throws Exception {

	}

	@Override
	public void setBeanName(String name) {

	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {

	}
}
