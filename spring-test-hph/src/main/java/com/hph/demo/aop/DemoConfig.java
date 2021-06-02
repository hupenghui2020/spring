package com.hph.demo.aop;

import com.hph.demo.importBeanDefinitionRegistrar.ImportBeanDefinitionRegistrarTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

/**
 * @author hph
 */
@ComponentScan("com.hph.demo.aop")
public class DemoConfig {
}
