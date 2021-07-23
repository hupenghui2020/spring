package com.hph.demo.aop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author hph
 */
@Component
public class AspectService implements IAspectService{

	@Autowired
	private B b;

	@Override
	public void testAop1(){

		System.out.println(b);
		System.out.println("测试aop！！！！！！！！");
	}

	@Override
	public void testAop2(){

		System.out.println("测试aop！！！！！！！！");
	}

	@Override
	public void testAop3(){
		System.out.println("测试aop！！！！！！！！");
	}
}
