package com.hph.demo.beanDefinitionRegistryPostProcessor.nameGenerator;

import org.springframework.context.annotation.ComponentScan;

/**
 * @author hph
 */
@ComponentScan(nameGenerator = CustomAnnNameGenerator.class)
public class ComponentScanTest {
}
