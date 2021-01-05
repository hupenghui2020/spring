package com.hph.demo;

import com.hph.demo.aop.AspectService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author hph
 */
public class Test {

	public static void main(String[] args) {

		//Y y = new Y();
		AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext();
		//ac.addBeanFactoryPostProcessor(new DemoOrderBeanFactoryPostProcessor());
		// 容器还未初始化之前把自定义的 bean 放入容器中
		//ac.getBeanFactory().registerSingleton("y", y);
		// ac.addBeanFactoryPostProcessor(new ExtraPointTest1());
		ac.register(DemoConfig.class);
		ac.refresh();
		ac.getBean(H.class).print();
		// ac.getBean(Z.class).print();
		//ac.getBean(W.class).print();
		ac.getBean(AspectService.class).testAop();
	}
}
