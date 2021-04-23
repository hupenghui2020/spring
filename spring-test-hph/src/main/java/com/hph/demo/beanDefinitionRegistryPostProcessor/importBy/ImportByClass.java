package com.hph.demo.beanDefinitionRegistryPostProcessor.importBy;

import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;

/**
 * @author hph
 */
@Component
@Import(ImportClass.class)
public class ImportByClass {

	/*@Component
	private static class InnerClass {

	}*/
}
