package com.hph.demo.di;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class DITeat {

	public static void main(String[] args) {

		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
		applicationContext.register(Config.class);
		applicationContext.refresh();
		applicationContext.getBean(A.class).print();
		applicationContext.getBean(B.class).print();
	}
}
