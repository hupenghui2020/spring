package com.hph.demo.config;

import com.hph.demo.beanFactoryPostProcessor.ImportBeanDefinitionRegistrarTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

/**
 * @author hph
 */
@ComponentScan("com.hph.demo")
@Import(ImportBeanDefinitionRegistrarTest.class)
public class DemoConfig {
}
