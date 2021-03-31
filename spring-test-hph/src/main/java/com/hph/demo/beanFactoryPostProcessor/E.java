package com.hph.demo.beanFactoryPostProcessor;

import org.springframework.beans.factory.annotation.Autowired;

public class E {

	@Autowired
	private B c;

	public void print(){
		System.out.println("xxxxx" + c);
	}
}
