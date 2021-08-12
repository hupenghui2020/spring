package com.hph.demo.jdbc;

import com.hph.demo.jdbc.mybatis.mapper.UserMapper;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author hph
 */
public class ApplicationTest {

	public static void main(String[] args) {

		/*AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext();
		ac.register(DemoConfig.class);
		// 直接将实例对象注册到singletonObjects中
		// ac.getBeanFactory().registerSingleton("d", new D());
		ac.refresh();
		ac.getBean(Eibdr.class).print();
		ac.getBean(Abfpp.class).print();*/
		ApplicationContext ac = new ClassPathXmlApplicationContext("application.xml");
		// 测试jdbc
		UserService userService = ac.getBean(UserServiceImpl.class);
		User user = new User("sdawda", 20, "fe");
		userService.save(user);
		User personl = userService.getUser();
		System.out.println(personl.toString());
		System.out.println("--------------- mybatis 测试 --------------------");
		UserMapper userMapper = ac.getBean(UserMapper.class);
		System.out.println(userMapper.getUser("sdawda").toString());

	}
}
