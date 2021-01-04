package com.hph.demo;

import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author hph
 */
@Component
public class A{

	X x;

	Y y;

	public void setX(X ix) {
		System.out.println("x setter");
		this.x = ix;
	}

	public A() {
		System.out.println("无参构造");
	}

	public A(X x) {
		System.out.println("有一个参数构造");
		this.x = x;
	}

	public A(X x,Y y) {
		System.out.println("有两个参数构造");
		this.x = x;
	}

	public void print() {
		System.out.println(x + "++++++");
	}
}
