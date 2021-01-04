package com.hph.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

/**
 * @author hph
 */
@Component
public class H {

	@Autowired
	private Z z;

	/*public void setZ(Z z) {
		System.out.println("Z是通过set方法注入进来的11111111111111111111111");
		this.z = z;
	}*/

	/*public H(Z z) {
		System.out.println("构造 H 注入 Z 属性成功！！！1");
		this.z = z;
	}*/

	public void print() {
		System.out.println("z----------------：" + z);
	}
}
