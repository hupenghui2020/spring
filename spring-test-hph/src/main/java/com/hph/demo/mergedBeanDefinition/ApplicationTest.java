package com.hph.demo.mergedBeanDefinition;

import org.springframework.beans.factory.support.*;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 每次对 bean 进行实例化的时候，都会拿 beanDefinitionMap 中的 beanDefinition 与 mergedBeanDefinitions 中的 mergedBeanDefinition 进行合并
 * 为什么：因为如果直接拿 mergedBeanDefinitions 中的 mergedBeanDefinition 进行 bean 的实例化的话，
 * 不能确保 beanDefinitionMap 中的 beanDefinition 被修改过（比如使用 BeanFactoryPostProcessor 进行修改）
 * 所以 mergedBeanDefinitions 是作为最近一次合并的结果进行缓存的作用
 * (合并：和继承的作用一样)
 * @author hph
 */
public class ApplicationTest {

	public static void main(String[] args) {

		// 初始化容器对象的时候就会将一些内部的 BeanDefinitionPostProcessor 进行注册
		AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext();
		ac.register(DemoConfig.class);

		/*// 第一种方式
		RootBeanDefinition rbd = new RootBeanDefinition();
		rbd.setBeanClass(Ambd.class);
		rbd.setScope(AbstractBeanDefinition.SCOPE_PROTOTYPE);

		// 设置 rbd 为 cbd 的父beanDefinition
		ChildBeanDefinition cbd = new ChildBeanDefinition("rbd");
		cbd.setBeanClass(Bmbd.class);
		cbd.setScope(AbstractBeanDefinition.SCOPE_SINGLETON);
		ac.registerBeanDefinition("rbd", rbd);
		ac.registerBeanDefinition("cbd", cbd);*/

		/*// 第二种方式
		GenericBeanDefinition gbd1 = new GenericBeanDefinition();
		gbd1.setBeanClass(Ambd.class);
		// 设置原型范围
		gbd1.setScope(AbstractBeanDefinition.SCOPE_PROTOTYPE);

		// 设置 gbd1 为 gbd2 的父beanDefinition
		GenericBeanDefinition gbd2 = new GenericBeanDefinition(gbd1);
		gbd2.setBeanClass(Bmbd.class);

		ac.registerBeanDefinition("gbd1", gbd1);
		ac.registerBeanDefinition("gbd2", gbd2);*/
		ac.refresh();
		ac.getBean(Ambd.class).test();
		System.out.println("------------------------------");
	}
}
