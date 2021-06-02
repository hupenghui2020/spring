package com.hph.demo.aop;

import com.hph.demo.importBeanDefinitionRegistrar.Eibdr;
import com.hph.demo.beanFactoryPostProcessor.freezeConfiguration.Abfpp;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author hph
 */
public class ApplicationTest {

	public static void main(String[] args) {

		AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext();
		ac.register(DemoConfig.class);
		ac.refresh();
		ac.getBean(IAspectService.class).testAop1();
	}
}
