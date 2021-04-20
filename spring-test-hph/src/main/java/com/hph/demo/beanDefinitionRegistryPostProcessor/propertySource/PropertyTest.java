package com.hph.demo.beanDefinitionRegistryPostProcessor.propertySource;

import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @author hph
 */
@Component
@PropertySource({"classpath:spring.properties", "classpath:spring-2.properties"})
public class PropertyTest {
}
