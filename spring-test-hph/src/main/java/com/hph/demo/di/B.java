package com.hph.demo.di;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class B {

	@Autowired
	private A a;

	public void print(){

		System.out.println("B注入了A: " + a);
	}
}
