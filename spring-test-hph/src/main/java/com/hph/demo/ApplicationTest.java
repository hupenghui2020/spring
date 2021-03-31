package com.hph.demo;

import com.hph.demo.beanFactoryPostProcessor.E;
import com.hph.demo.config.DemoConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author hph
 */
public class ApplicationTest {

	public static void main(String[] args) {

		AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext();
		ac.register(DemoConfig.class);
		ac.refresh();
		ac.getBean(E.class).print();
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
