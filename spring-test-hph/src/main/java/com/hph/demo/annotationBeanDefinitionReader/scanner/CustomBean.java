package com.hph.demo.annotationBeanDefinitionReader.scanner;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义注解（通过自定义的扫描器扫描并加载到spring容器）
 * @author hph
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface CustomBean {
}
