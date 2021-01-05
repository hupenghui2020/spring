package com.hph.demo;

import org.springframework.beans.factory.BeanClassLoaderAware;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.stereotype.Component;

/**
 * 扩展点8
 * 通过实现 BeanClassLoaderAware，可以拿到当前 bean 的 ClassLoader
 * @author hph
 */
@Component
public class ExtraPointTest8 implements BeanClassLoaderAware {

	@Override
	public void setBeanClassLoader(ClassLoader classLoader) {
		System.out.println("ExtraPointTest8 setBeanClassLoader");
	}
}
