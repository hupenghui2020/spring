package com.hph.demo.di;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class A {

	@Autowired
	private B b;

	public void print(){

		System.out.println("A注入了B: " + b);
	}
}
