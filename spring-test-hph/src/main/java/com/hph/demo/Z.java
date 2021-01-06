package com.hph.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class Z {

	//@Autowired
	private H h;

	/*public Z(H h) {
		System.out.println("构造器方法注入参数h");
		this.h = h;
	}*/

	/*public void setH(H h) {
		System.out.println("Z 注入 H 属性成功！！！1");
		this.h = h;
	}*/

	public void print(){
		System.out.println("h：" + h);
	}


}
