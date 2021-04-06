package com.hph.demo.annotationBeanDefinitionReader.beanNameGenerator;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * 只扫描sub包，测试不同的beanDefinition渲染方式
 * 这个是通过refresh来扫描的
 * @author hph
 */
@Configuration
@ComponentScan("com.hph.demo.annotationBeanDefinitionReader.beanNameGenerator.sub")
public class DemoConfig {
}
