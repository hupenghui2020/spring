package com.hph.demo.applicationContextAware;

import com.hph.demo.importBeanDefinitionRegistrar.ImportBeanDefinitionRegistrarTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

/**
 * @author hph
 */
@ComponentScan("com.hph.demo")
@Import(ImportBeanDefinitionRegistrarTest.class)
public class DemoConfig {
}
