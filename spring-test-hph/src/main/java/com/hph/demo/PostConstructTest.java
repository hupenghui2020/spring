package com.hph.demo;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author hph
 */
@Component
public class PostConstructTest {

	@PostConstruct
	public void postConstruct(){
		System.out.println("测试 @PostConstruct 注解方法");
	}
}
