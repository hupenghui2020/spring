package com.hph.demo;

import org.springframework.beans.factory.BeanNameAware;
import org.springframework.stereotype.Component;

/**
 * 扩展点7
 * 通过实现 BeanNameAware，可以拿到当前 bean 的 beanName
 * @author hph
 */
@Component
public class ExtraPointTest7 implements BeanNameAware {

	@Override
	public void setBeanName(String name) {
		System.out.println("实现了 BeanNameAware 接口：" + name);
	}
}
