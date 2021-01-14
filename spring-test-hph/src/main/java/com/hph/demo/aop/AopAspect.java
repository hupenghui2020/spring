package com.hph.demo.aop;

import com.hph.demo.H;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.DeclareParents;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @author hph
 */
@Aspect
@Component
public class AopAspect {

	@Pointcut("execution(* com.hph.demo.aop.AspectService.*())")
	public void testPoint() {

	}

	@Before("testPoint()")
	public void testBefore(){

		System.out.println("我是 before 通知！！！！！！！1");
	}
}
