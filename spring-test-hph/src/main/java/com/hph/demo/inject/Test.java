package com.hph.demo.inject;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.beans.IntrospectionException;

public class Test {

	public static void main(String[] args) throws IntrospectionException {

		AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext();
		ac.register(A.class,B.class,D.class,F.class);
		ac.refresh();
	}
}
