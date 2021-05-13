package com.hph.demo.beanPostProcesor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * @author hph
 */
@Component
public class BeanPostProcessorTest implements BeanPostProcessor {

	@Autowired
	private SpringRegisterUtil springRegisterUtil;

	@Autowired
	private SpringUtil springUtil;

	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {

		springRegisterUtil.registerBean("xxx", A.class);
		if(!"xxx".equals(beanName)) {
			springUtil.getBean(A.class).test();
		}
		return null;
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		return null;
	}
}
