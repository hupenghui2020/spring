package com.hph.demo.mergedBeanDefinition;

import org.springframework.stereotype.Component;

@Component
public class Ambd {

	static{
		System.out.println("测试111111111");
		try {
			Class.forName("com.hph.demo.mergedBeanDefinition.Bmbd");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
