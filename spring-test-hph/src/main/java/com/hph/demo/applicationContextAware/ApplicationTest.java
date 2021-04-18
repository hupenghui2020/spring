package com.hph.demo.applicationContextAware;

import com.hph.demo.importBeanDefinitionRegistrar.Eibdr;
import com.hph.demo.beanFactoryPostProcessor.freezeConfiguration.Abfpp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author hph
 */
public class ApplicationTest {

	@Autowired
	private ApplicationContextAwareTest applicationContextAwareTest;

	public static void main(String[] args) {

		AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext();
		ac.register(DemoConfig.class);
		// 直接将实例对象注册到singletonObjects中
		// ac.getBeanFactory().registerSingleton("d", new D());
		ac.refresh();
		System.out.println();
		// ApplicationContext ac = new ClassPathXmlApplicationContext("application.xml");
		// 测试jdbc
		/*UserService userService = ac.getBean(UserServiceImpl.class);
		User user = new User("sdawda", 20, "fe");
		userService.save(user);
		User personl = userService.getUser();
		System.out.println(personl.toString());
		System.out.println("--------------- mybatis 测试 --------------------");
		UserMapper userMapper = ac.getBean(UserMapper.class);
		System.out.println(userMapper.getUser("sdawda").toString());*/

	}
}
