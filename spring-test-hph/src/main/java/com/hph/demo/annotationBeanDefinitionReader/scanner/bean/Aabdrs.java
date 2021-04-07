package com.hph.demo.annotationBeanDefinitionReader.scanner.bean;

import com.hph.demo.annotationBeanDefinitionReader.scanner.CustomBean;

/**
 * 通过自定义扫描器来扫描进行注册到spring容器中
 * @author hph
 */
@CustomBean
public class Aabdrs {

	public Aabdrs() {
		System.out.println("Aabdrs++++++++++++");
	}
}
