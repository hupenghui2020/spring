package com.hph.demo.beanFactoryPostProcessor;

/**
 * @author hph
 */
public class A {

	private B b;

	public void setB(B b) {
		this.b = b;
	}

	public void print(){

		System.out.println("+++" + b);
	}
}
