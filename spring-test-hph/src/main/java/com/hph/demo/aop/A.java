package com.hph.demo.aop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class A {

	@Autowired
	private AspectService aspectService;

	private void print() {

		System.out.println(aspectService);
	}
}
