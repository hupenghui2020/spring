package com.hph.demo.inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class A {

	private B b;

	private C c;

	/**
	 * 所以不管是通过类型注入还是通过名称注入，都是指的属性的类型或名称
	 * @param b
	 */
	@Autowired
	public void testB(B b) {

		System.out.println("b = " + b);
		this.b = b;
	}

	@Autowired
	@Qualifier("f")
	public void TestC(C c) {

		System.out.println("c = " + c);
		this.c = c;
	}
}
