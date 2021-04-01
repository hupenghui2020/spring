package com.hph.demo.importBeanDefinitionRegistrar;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

/**
 * @author hph
 */
@ComponentScan("com.hph.demo.importBeanDefinitionRegistrar")
@Import(ImportBeanDefinitionRegistrarTest.class)
public class DemoConfig {
}
