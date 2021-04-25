package com.hph.demo.beanDefinitionRegistryPostProcessor.importBy;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * @author hph
 */
//@Configuration
public class A {

	@Component
	class C {

		public C() {
			// 这个不会打印，C不符合配置类的条件
			System.out.println("cccccccccccccc");
		}
	}

	public A() {
		System.out.println("aaaaaaaaaaaaaaaa");
	}
}
