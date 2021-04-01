package com.hph.demo.importBeanDefinitionRegistrar;

import org.springframework.beans.factory.annotation.Autowired;

public class Eibdr {

	@Autowired
	private Bibdr c;

	public void print(){
		System.out.println("xxxxx" + c);
	}
}
