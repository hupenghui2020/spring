package com.hph.demo.mergedBeanDefinition;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Ambd {


	@Autowired
	private Bmbd bmbd;

	static{
		System.out.println("测试111111111");
		try {
			Class.forName("com.hph.demo.mergedBeanDefinition.Bmbd");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	/*@Autowired
	private void xxx(Bmbd bmbd) {
		this.bmbd = bmbd;
	}*/

	public void test() {
		bmbd.test();
	}
}
