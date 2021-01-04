package com.hph.demo;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.stereotype.Component;

/**
 * 扩展点9
 * 通过实现 BeanClassLoaderAware，可以拿到 BeanFactory
 * @author hph
 */
@Component
public class ExtraPointTest10_1   {
}
