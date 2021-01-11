package com.hph.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author hph
 */
@Component
public class H {

	@Resource(type = Dz.class)
	private Z z;

	// @Resource(name = "dz")
	private W w;

	/*@Autowired
	public void setZ(Z z, W w) {
		this.z = z;
		this.w = w;
		System.out.println("Z是通过set方法注入进来的11111111111111111111111" + this.z);
		System.out.println("W是通过set方法注入进来的11111111111111111111111" + this.w);
	}*/

	/*public H(@Qualifier("dz") Z z, W w) {
		this.z = z;
		this.w = w;
		System.out.println("构造 H 注入 Z 属性成功！！！" + this.z);
		System.out.println("构造 H 注入 W 属性成功！！！" + this.w);
	}*/

	public void print() {
		System.out.println("z----------------：" + z);
	}
}
