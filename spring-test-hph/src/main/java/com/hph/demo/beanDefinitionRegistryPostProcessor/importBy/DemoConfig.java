package com.hph.demo.beanDefinitionRegistryPostProcessor.importBy;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

/**
 * @author hph
 */
@ComponentScan(value = "com.hph.demo.beanDefinitionRegistryPostProcessor.importBy",
		excludeFilters = {@ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = ImportClass.class),
				@ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = A.class)})
public class DemoConfig {
}
