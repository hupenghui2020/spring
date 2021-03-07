package com.hph.demo;

import com.hph.demo.aop.IAspectService;
import com.hph.demo.jdbc.User;
import com.hph.demo.jdbc.UserService;
import com.hph.demo.jdbc.UserServiceImpl;
import com.hph.demo.jdbc.mybatis.mapper.UserMapper;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

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
		// register只是把bd放入bd的容器中，并不会进行bean实例的创建
		ac.register(DemoConfig.class);
		ac.refresh();
		ac.getBean(H.class).print();
		// ac.getBean(Z.class).print();
		//ac.getBean(W.class).print();
		ac.getBean(IAspectService.class).testAop1();
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
