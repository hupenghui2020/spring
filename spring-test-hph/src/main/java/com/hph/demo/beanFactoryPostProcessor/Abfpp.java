package com.hph.demo.beanFactoryPostProcessor;

import org.springframework.stereotype.Component;

/**
 * @author hph
 */
@Component
public class Abfpp {

	public Abfpp() {

		System.out.println("来了老弟！！！");
	}

	public void print() {
		System.out.println("dddddddddddddd");
	}
}
