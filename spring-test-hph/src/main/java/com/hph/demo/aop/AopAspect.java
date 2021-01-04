package com.hph.demo.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @author hph
 */
@Aspect
@Component
public class AopAspect {

	@Pointcut("execution(* com.hph.demo.aop.AspectService.testAop())")
	public void testPoint() {

	}

	@Before("testPoint()")
	public void testBefore(){

		System.out.println("我是 before 通知！！！！！！！1");
	}
}
