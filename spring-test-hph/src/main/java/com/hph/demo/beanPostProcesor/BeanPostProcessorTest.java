package com.hph.demo.beanPostProcesor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.core.Ordered;
import org.springframework.core.PriorityOrdered;
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

		// 即使A被注册且被实例化了，就算
		/*springRegisterUtil.registerBean("xxx", A.class);
		if(!"xxx".equals(beanName)) {
			springUtil.getBean(A.class).test();
		}*/
		System.out.println("springRegisterUtil" + springRegisterUtil);
		System.out.println("springUtil" + springUtil);
		return null;
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		return null;
	}
}
