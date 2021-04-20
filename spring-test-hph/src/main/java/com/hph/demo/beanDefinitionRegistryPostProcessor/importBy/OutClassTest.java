package com.hph.demo.beanDefinitionRegistryPostProcessor.importBy;

import org.springframework.stereotype.Component;

/**
 * @author 10499
 */
@Component
public class OutClassTest {

	@Component
	private static class InnerClass {

	}
}
