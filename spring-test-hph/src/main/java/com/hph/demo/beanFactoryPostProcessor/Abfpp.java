package com.hph.demo.beanFactoryPostProcessor;

import org.springframework.stereotype.Component;

/**
 * @author hph
 */
@Component
public class Abfpp {

	private Dbfpp bbfpp;

	public void setDbfpp(Dbfpp bbfpp) {
		this.bbfpp = bbfpp;
	}

	public void print() {
		System.out.println("dddddddddddddd" + bbfpp);
	}
}
